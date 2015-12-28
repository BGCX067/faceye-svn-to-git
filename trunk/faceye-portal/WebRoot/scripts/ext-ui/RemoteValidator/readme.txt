Information

This plugin adds remote validation support to form fields in addition to standard client side validation.
ToDo

Better documentation.
rvOptions

rvOptions is passed on to Ext.Ajax.request and is an object which may contain the following properties:

    * url : String (Optional)
    * params : Object/String/Function
    * method : String (Optional)
    * callback : Function (Optional)
          o options : Object
          o success : Boolean
          o response : Object 

    * success : Function (Optional)
          o response : Object
          o options : Object 
    * failure : Function (Optional)
          o response : Object
          o options : Object 
    * scope : Object (Optional)
    * form : Object/String (Optional)
    * isUpload : Boolean (Optional)
    * headers : Object (Optional)
    * xmlData : Object (Optional)
    * jsonData : Object/String (Optional)
    * disableCaching : Boolean (Optional) 

The rvOptions object may also contain any other property which might be needed to perform postprocessing in a callback because it is passed to callback functions. 