package com.faceye.core.web.controller;

import com.faceye.core.dao.hibernate.model.BaseObject;
import com.faceye.core.service.security.model.Tree;
import com.faceye.core.service.security.service.iface.ITreeService;

public class TreeActionSupport extends BaseActionSupportTemplate<Tree,ITreeService> {
public String test(){
   Tree tree= this.getO();
     System.out.println(tree.getName());
    return super.SUCCESS;
}
public static void main(String[] args) {
    Tree  tree=(Tree) new BaseObject();
    tree.setName("j5");
    TreeActionSupport ta=new TreeActionSupport();
    ta.setO(tree);    ta.save();
}
}
