Some Eamples Of RowAction User:

var action = new Ext.ux.grid.RowAction({iconCls:'xxx',qtip:'yyy'});
var grid = new Ext.grid.GridPanel({
    columns:[action, ....],
    plugins:[action, ....]
});
action.on('action', function(grid, record) {...});  

---------------------------------------
    ,initComponent:function() {

        this.actionOpen = new Ext.ux.grid.RowAction({
             iconCls:'icon-go-tab'
            ,qtip:this.openSetText
        });
        this.actionDelete = new Ext.ux.grid.RowAction({
             iconCls:'icon-trash-closed'
            ,qtip:this.deleteText
        });
        Ext.apply(this, {
            ......
            ,plugins:[
                 new Ext.ux.grid.Search({position:'top'})
                ,this.actionDelete
                ,this.actionOpen
            ]
        .....  