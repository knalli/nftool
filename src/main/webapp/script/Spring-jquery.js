/*Quelle: http://jira.springframework.org/browse/SJS-4*/

Spring.RemotingHandler = function(){}

Spring.RemotingHandler.prototype = new Spring.AbstractRemotingHandler();

Spring.RemotingHandler.prototype.xhr = null;

Spring.RemotingHandler.prototype.submitForm = function(sourceId, formId, params){
	// /*String */, /*String*/, /*Object*/ 
	if(formId.length == 0) {
		console.error("Cannot use Spring.remoting.submitForm without formId specified");
	}
	
	var content = new Object();
	
	for (var key in params) {
		content[key] = params[key];
	}
	
	if(typeof sourceId == "string" && sourceId.length>0) {
		var sourceComponent = $("#"+sourceId);
	
	    if (sourceComponent != null){
	    	if(sourceComponent.value != undefined && sourceComponent.type && ("button,submit,reset").indexOf(sourceComponent.type) < 0) {
				content[sourceId] = sourceComponent.value;
			}
			else if(sourceComponent.name != undefined) {
	    		content[sourceComponent.name] = sourceComponent.name;
	    	} else {
	    		content[sourceId] = sourceId;
	    	}
	    }
	}
    
	if (!content['ajaxSource']) {
		content['ajaxSource'] = sourceId;
	}
	
	$.each($("#"+formId).serializeArray(), function(i, field){
		content[field.name]= field.value;
	});
	
	Spring.RemotingHandler.xhr = $.ajax({
    	beforeSend: function (xhr) {
    		xhr.setRequestHeader("Accept","text/html;type=ajax");
    	},
    	url: $("#"+formId).attr("action"),
    	data: content,
    	success: this.handleResponse,
    	error: this.handleError,
    	dataType: 'text',
    	type: 'POST'
    });
};

Spring.RemotingHandler.prototype.handleResponse = function(response, textStatus) {
	var redirectURL = Spring.RemotingHandler.xhr.getResponseHeader('Spring-Redirect-URL');
	var modalViewHeader = Spring.RemotingHandler.xhr.getResponseHeader('Spring-Modal-View');
	var modalView = (((typeof modalViewHeader) == "string" && modalViewHeader.length > 0));//|| ioArgs.args.modal
	
	if((typeof redirectURL) == "string" && redirectURL.length > 0) {
		if (modalView) {
			//render a popup with the new URL
			//Spring.remoting.renderURLToModalDialog(redirectURL, ioArgs);
			alert("modal view not supported yet");
			return response;
		} else {
			if (redirectURL.indexOf("/") >= 0) {
				window.location = window.location.protocol + "//" + window.location.host + redirectURL;
			} else {
				var location = window.location.protocol + "//" + window.location.host + window.location.pathname;
				var appendIndex = location.lastIndexOf("/");
				location = location.substr(0,appendIndex+1) + redirectURL;
				if (location == window.location) {
					alert("relative redirect is not supported yet");
					//Spring.remoting.getResource(location, ioArgs.args.content, false);
				}
				else {
					window.location = location;
				}
			}
			return response;		
		}
	}
	
	$(response).each(function(){
		if(this.tagName && this.tagName.toUpperCase() == "SCRIPT") {
			eval(this.text);
		}
	});
	
	response = response.replace(new RegExp('(?:<script(.|[\n|\r])*?>)((\n|\r|.)*?)(?:<\/script>)', 'img'), '');
	
	$(response).each(function() {
		if(this.id != null && this.id != "") {
			var target = $("#" + this.id);
			if(0 ==target.length) {
				console.error("An existing DOM elment with id '" + this.id + "' could not be found for replacement.")
			} else {
				target.html(this.innerHTML);
			}
		} else {
			console.warn("Got node '" + this.tagName + "' without id attribute. Skipping...");
		}
	});
	return response;
};

Spring.RemotingHandler.prototype.handleError = function(xhr, textStatus, errorThrown) {
	console.error("HTTP status code: ", xhr.status);
	
	if (Spring.debug && xhr.status != 200) {
		var dialog = $("<iframe style=\"min-width: 600px;\" id='dialogFrame'></iframe>");
		$("body").append(dialog);
		dialog.dialog({height: 480, width: 640, modal: true});
		dialog[0].contentDocument.open();
		dialog[0].contentDocument.write(xhr.responseText);
		dialog[0].contentDocument.close();
	}
};

Spring.RemotingHandler.getLinkedResource = function(linkId, params, modal) {
	// /*String */, /*Object*/, /*boolean*/ 
	this.getResource($("#"+linkId).attr("href"), params, modal);
};

Spring.RemotingHandler.getResource = function(resourceUri, params, modal) {
	// /*String */, /*Object*/, /*boolean*/ 
	Spring.RemotingHandler.xhr = $.ajax({
    	beforeSend: function (xhr) {
    		xhr.setRequestHeader("Accept","text/html;type=ajax");
    	},
    	url: resourceUri,
    	data: params,
    	success: this.handleResponse,
    	error: this.handleError,
    	dataType: 'text',
    	type: 'GET'
    });

};

Spring.RemotingHandler.prototype.renderURLToModalDialog = function(url, ioArgs) {
	alert("renderURLToModalDialog not implemented");
};

Spring.RemotingHandler.prototype.renderNodeListToModalDialog = function(nodes) {
	var dialog = $(nodes);
	dialog.dialog({height: 480, width: 640, modal: true});
};

$(document).ready(function(){Spring.initialize()});