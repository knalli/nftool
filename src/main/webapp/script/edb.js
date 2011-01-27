// This Requires jQuery has bein loaded
$(function(){
	// DOM is ready
	
	/**
	 * Helper to enable/disable debug code.
	 * Defines console, if it does not exist.
	 */
	var init = function(){
		if (window.console == undefined) {
			window.console = {
					log: function(){},
					error: function(){},
					warn: function(){}
			};
		}
	};
	
	init();
	
});