package com.faceye.core.service.security.service.intercept;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.faceye.core.service.security.cache.iface.IResourceCacheService;
import com.faceye.core.service.security.model.Resource;

public class WebFilterInvocationDefinitionSource extends
		AbstractFilterInvocationDefinitionSource {
	private IResourceCacheService resourceCacheService;

	private boolean useAntPath = false;
	
	private boolean convertUrlToLowercaseBeforeComparison=false;

	private PathMatcher pathMatcher = new AntPathMatcher();

	private PatternMatcher matcher = new Perl5Matcher();

	public ConfigAttributeDefinition lookupAttributes(String arg0) {
		// TODO Auto-generated method stub
		this.getResourceCacheService().initResourceCache();
		List urlResources = this.getResourceCacheService().getUrlResourceStrings();
		GrantedAuthority[] authorities = null;
//		if (this.isUseAntPath()) {
//			int temp = arg0.lastIndexOf("?");
//			if (temp != -1) {
//				arg0 = arg0.substring(0, temp);
//			}
//		}
		Iterator it = urlResources.iterator();
		while (it.hasNext()) {
			Resource item = (Resource) it.next();
			boolean matched = false;
			String str=item.getResourceStr()+"*";
			if (this.isUseAntPath()) {
				matched = pathMatcher.match(str, arg0);
			} else {
				Pattern compiledPattern;
				Perl5Compiler compiler = new Perl5Compiler();
				try {
					compiledPattern = compiler.compile(item.getResourceStr(),
							Perl5Compiler.READ_ONLY_MASK);
				} catch (MalformedPatternException mpe) {
					throw new IllegalArgumentException(
							"Malformed regular expression: "
									+ item.getResourceStr());
				}
				matched = matcher.matches(arg0, compiledPattern);
			}
			if (matched) {
				authorities = item.permission2GrantedAuthority();
				break;
			}
		}
		if (authorities != null && authorities.length > 0) {
			String authoritiesStr = "";
			for (int i = 0; i < authorities.length; i++) {
				authoritiesStr += authorities[i].getAuthority() + ",";
			}
			String authStr = authoritiesStr.substring(0, authoritiesStr
					.length() - 1);
			ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
			configAttrEditor.setAsText(authStr);
			return (ConfigAttributeDefinition) configAttrEditor.getValue();

		}
		return null;
	}

	public Iterator getConfigAttributeDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	

	public boolean isUseAntPath() {
		return useAntPath;
	}

	public void setUseAntPath(boolean useAntPath) {
		this.useAntPath = useAntPath;
	}

	public boolean isConvertUrlToLowercaseBeforeComparison() {
		return convertUrlToLowercaseBeforeComparison;
	}

	public void setConvertUrlToLowercaseBeforeComparison(
			boolean convertUrlToLowercaseBeforeComparison) {
		this.convertUrlToLowercaseBeforeComparison = convertUrlToLowercaseBeforeComparison;
	}

	public IResourceCacheService getResourceCacheService() {
		return resourceCacheService;
	}

	public void setResourceCacheService(IResourceCacheService resourceCacheService) {
		this.resourceCacheService = resourceCacheService;
	}

}
