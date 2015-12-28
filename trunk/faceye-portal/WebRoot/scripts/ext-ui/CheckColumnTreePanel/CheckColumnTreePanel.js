Ext.ux.CheckColumnTreePanel = Ext.extend(Ext.tree.TreePanel, {
  lines : false,
  borderWidth : Ext.isBorderBox ? 0 : 2, // the combined left/right border
    // for each cell
    cls : 'x-column-tree',
    checkColHeader : 'Add',
    checkColWidth : 30,

    onRender : function() {
      Ext.ux.CheckColumnTreePanel.superclass.onRender.apply(this,
          arguments);
      this.headers = this.body.createChild( {
        cls : 'x-tree-headers'
      }, this.innerCt.dom);

      var cb = typeof this.root.attributes.checked == 'boolean';

      var cols = this.columns, c;
      var totalWidth = 0;

      if (cb)
        cols.push( {
          header : this.checkColHeader,
          width : this.checkColWidth
        });

      for (var i = 0, len = cols.length;i < len; i++) {
        c = cols[i];
        totalWidth += c.width;
        this.headers.createChild( {
          cls : 'x-tree-hd ' + (c.cls ? c.cls + '-hd' : ''),
          cn : {
            cls : 'x-tree-hd-text',
            html : c.header
          },
          style : 'width:' + (c.width - this.borderWidth) + 'px;'
        });
      }
      this.headers.createChild( {
        cls : 'x-clear'
      });
      // prevent floats from wrapping when clipped
      this.headers.setWidth(totalWidth);
      this.innerCt.setWidth(totalWidth);
    }
  });

Ext.ux.CheckColumnNodeUI = Ext.extend(Ext.tree.TreeNodeUI,{
          focus : Ext.emptyFn, // prevent odd scrolling behavior

          renderElements : function(n, a, targetNode, bulkRender) {
            this.indentMarkup = n.parentNode ? n.parentNode.ui
                .getChildIndent() : '';

            var t = n.getOwnerTree();
            var cols = t.columns;
            var bw = t.borderWidth;
            var c = cols[0];

            var cb = typeof t.root.attributes.checked == 'boolean';
            t.root.attributes.checked = false;

            var buf = [
                '<li class="x-tree-node"><div ext:tree-node-id="',
                n.id,
                '" class="x-tree-node-el x-tree-node-leaf ',
                a.cls,
                '">',
                '<div class="x-tree-col" style="width:',
                c.width - bw,
                'px;">',
                '<span class="x-tree-node-indent">',
                this.indentMarkup,
                "</span>",
                '<img src="',
                this.emptyIcon,
                '" class="x-tree-ec-icon x-tree-elbow">',
                '<img src="',
                a.icon || this.emptyIcon,
                '" class="x-tree-node-icon',
                (a.icon ? " x-tree-node-inline-icon" : ""),
                (a.iconCls ? " " + a.iconCls : ""),
                '" unselectable="on">',
                '<a hidefocus="on" class="x-tree-node-anchor" href="',
                a.href ? a.href : "#",
                '" tabIndex="1" ',
                a.hrefTarget
                    ? ' target="' + a.hrefTarget + '"'
                    : "",
                '>',
                '<span unselectable="on">',
                n.text
                    || (c.renderer
                        ? c.renderer(a[c.dataIndex], n,
                            a)
                        : a[c.dataIndex]),
                "</span></a>", "</div>"];
            for (var i = 1, len = cols.length;i < len; i++) {
              c = cols[i];

              buf
                  .push(
                      '<div class="x-tree-col ',
                      (c.cls ? c.cls : ''),
                      '" style="width:',
                      c.width - bw,
                      'px;">',
                      '<div class="x-tree-col-text">',
                      (cb & i == cols.length - 1
                          ? '<div class="x-tree-col-checker'
                              + (a.checked
                                  ? ' x-tree-col-checked'
                                  : '"> </div>')
                          : (c.renderer ? c.renderer(
                              a[c.dataIndex], n,
                              a) : a[c.dataIndex])),
                      "</div>", "</div>");
            }

            buf
                .push(
                    '<div class="x-clear"></div></div>',
                    '<ul class="x-tree-node-ct" style="display:none;"></ul>',
                    "</li>");

            if (bulkRender !== true && n.nextSibling
                && n.nextSibling.ui.getEl()) {
              this.wrap = Ext.DomHelper.insertHtml("beforeBegin",
                  n.nextSibling.ui.getEl(), buf.join(""));
            } else {
              this.wrap = Ext.DomHelper.insertHtml("beforeEnd",
                  targetNode, buf.join(""));
            }

            this.elNode = this.wrap.childNodes[0];
            this.ctNode = this.wrap.childNodes[1];
            var cs = this.elNode.firstChild.childNodes;
            this.indentNode = cs[0];
            this.ecNode = cs[1];
            this.iconNode = cs[2];
            if (cb)
              this.checkbox = this.elNode.childNodes[4].firstChild.firstChild;
            this.anchor = cs[3];
            this.textNode = cs[3].firstChild;
          },

          onClick : function(e) {
          	alert('d');
            if (e.target.className == 'x-tree-col-checker') {
              e.stopEvent();
              var hd = Ext.fly(e.target.parentNode);
              var isChecked = hd.hasClass('x-tree-col-checked');
              if (isChecked) {
              	
                hd.removeClass('x-tree-col-checked');
                this.node.attributes.checked = false;
                this.fireEvent('checkchange', this.node, false);
              } else {
                hd.addClass('x-tree-col-checked');
                this.node.attributes.checked = true;
                this.fireEvent('checkchange', this.node, true);
              }
            }
            if (this.dropping) {
              e.stopEvent();
              return;
            }
            if (this.fireEvent("beforeclick", this.node, e) !== false) {
              var a = e.getTarget('a');
              if (!this.disabled && this.node.attributes.href
                  && a) {
                this.fireEvent("click", this.node, e);
                return;
              } else if (a && e.ctrlKey) {
                e.stopEvent();
              }
              e.preventDefault();
              if (this.disabled) {
                return;
              }

              if (this.node.attributes.singleClickExpand
                  && !this.animating
                  && this.node.hasChildNodes()) {
                this.node.toggle();
              }

              this.fireEvent("click", this.node, e);
            } else {
              e.stopEvent();
            }
          }
        });