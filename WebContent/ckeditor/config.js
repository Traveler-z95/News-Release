/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.language = 'zh-cn';   
    config.filebrowserBrowseUrl = 'ckeditor/uploader/browse.jsp';   
    config.filebrowserImageBrowseUrl = 'ckeditor/uploader/browse.jsp?type=Images';   
    config.filebrowserFlashBrowseUrl = 'ckeditor/uploader/browse.jsp?type=Flashs';   
    config.filebrowserUploadUrl = 'ckeditor/uploader/upload.jsp';   
    config.filebrowserImageUploadUrl = 'ckeditor/uploader/upload.jsp?type=Images';   
    config.filebrowserFlashUploadUrl = 'ckeditor/uploader/upload.jsp?type=Flashs';   
    config.filebrowserWindowWidth = '640';   
    config.filebrowserWindowHeight = '480';  
};
