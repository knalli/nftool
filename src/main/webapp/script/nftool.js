$(function(){
			console.log("Starting nftool.js ...");
			var draggableConfig = { 
				cursor:'pointer',
				revert: 'invalid',
				opacity: 0.35,
				helper:	function(event) {
					var table = $(this).parents('table');
					var columnNr = $(this).prevAll().length + 1;
					//var column = $('td:nth-child('+columnNr+'), th:nth-child('+columnNr+')', table);
					var column = $('th:nth-child('+columnNr+')', table);

					var helper = $('<table></table>').append(column.clone());
					$('td,th', helper).wrap('<tr></tr>');
					
					return $('<div class="draggable-helper"></div>').append(helper);
				}
			};
			
			var edbUiTags = {
					itemDiv : '<div class="item roundCorners"></div>',
					delSpan : '<span class="ui-icon ui-icon-circle-minus"></span>',
					keySpan : '<span class="ui-icon ui-icon-key"></span>',
					delKeySpan : '<span class="ui-icon ui-icon-key"></span>',
					columnSpan : '<span class="ui-icon ui-icon-arrow-4-diag"></span>'
			};
			
			var droppableContainer = '.column-droppable';
			var droppablesSelector = droppableContainer + ' .items, '+droppableContainer+' .keys';

			var droppableConfig = {
				greedy: true,
				hoverClass: 'drophover',
				addClasses: false,
				over: function (event, ui) {
					var txt = $('th', ui.helper).text();
					$(edbUiTags.itemDiv)
						.append(txt)
						.addClass('drophoveritem')
						.appendTo(this);
					$(this).addClass('has-items');
				},
				out: function (event, ui) {
					$('.drophoveritem', this).remove();
					if ($('.item', this).length < 1) {
						$(this).removeClass('has-items');
					}
				},
				drop: function (event, ui) {
					handleEnsureLastEmptyRelation();
					
					var item = $(this).find('.drophoveritem');
					
					handleFormChange(item, this);
					addColumnToRelation(item);
					
					// Finally, re-enable dd-configuration, even for the modified elements
					$(droppablesSelector).droppable(droppableConfig);
				}
			};
			
			/**
			 * Handles UI Update
			 * The drophoveritem-class is removed from target and
			 * the delete span tag and the handler were attached.
			 * If you give a target object, the item is attached to this and
			 * target class has-items is ensured.
			 * @param item jQuery-Element
			 * @param target jQuery-Element optional
			 */
			var addColumnToRelation = function(item, target) {
				if (target != null) {
					target.addClass('has-items').append(item);
				}
				
				// Cleanup and add delete control
				item
					.removeClass('drophoveritem')
					.append( 
						$(edbUiTags.delSpan)
							.click(trashItem)
							.hover(hoverItem, hoverItem) );
			};
			
			
			/**
			 * If we dropped to an empty relation, clone it, remove the
			 * drop-element and concat it to the end.
			 */
			var handleEnsureLastEmptyRelation = function(){
				var lastContainer = $(droppableContainer + ' > .container:last');
				if (lastContainer.find('.item').length > 0) {
					lastContainer
						.clone()
						.find('.item').remove().end()
						.children().removeClass('has-items').end()
						.appendTo('.column-droppable');
				}
			};

			/**
			 * Checks checkboxes in #ansewer to sync user-action in UI -> form.
			 */
			var handleFormChange = function(item, contextElement) {
				var context = $(contextElement);
				var id = item.text().trim();
				var relationNr = $(droppableContainer + ' > .container').index(context.parent());
				var containerPrefix, idPrefix;
				if (context.hasClass('keys')) {
					containerPrefix = 'div.keys';
					idPrefix = 'keys' + relationNr;
				} else {
					containerPrefix = 'div:not(.keys)';
					idPrefix = 'columns' + relationNr;
				}
				var map = $.data(document.body, 'edbTableMapping');
				var checkbox = $(containerPrefix + ' input[value="' + map[id] + '"][id^="' + idPrefix + '"]');
				checkbox.attr('checked', true);
				item.data({checkboxId : checkbox.attr('id')});
			};
			
			/**
			 * Callback while dd-element hovers a drop-zone
			 * @method hoverItem 
			 */
			var hoverItem = function() {
				$(this).parent().toggleClass('hovering');
			};

			/**
			 * Callback of an relation-item delete button.
			 * @method trashItem
			 */
			var trashItem = function() {
				var item = $(this).parent();
				var checkbox = $('#'+item.data().checkboxId);
				var keysOrItems = item.parent();
				item.remove();
				checkbox.attr('checked',false);
				if (keysOrItems.find('.item').length < 1) {
					keysOrItems.removeClass('has-items');
				}
				
				$(droppableContainer + ' .container:not(:last)').each(function(){
					if ($('.has-items', this).length < 1) {
						$(this).remove();
					}
				});
			};
			
			/*** Normalforms ***/
			$('table.exercise input[name=modify]').hide().attr('disabled',true);
			$('.showOnFalse').hide();
			
			$('input[type=radio]').change(function(){
				if ($(this).val() == 'false') {
					//Show controls
					$('table.exercise input[name=modify]').attr('disabled',false).fadeIn();
					$('.showOnFalse').fadeIn();
				} else {
					//Hide controls
					$('table.exercise input[name=modify]').hide().attr('disabled',true);
					$('.showOnFalse').hide();
				}
			});
			
			
			var readFormDataToUI = function() {
				var inputsChecked = $('#answer input[type=checkbox]:checked');
				console.log('Pre-checked items: ' + inputsChecked.length + ' (' + inputsChecked + ')');
				if (inputsChecked.length <= 0) {
					return;
				}
				var relation = -1, isKeyColumn;
				inputsChecked.each(function(i, elem){
					var thisRelation = readRelString(elem);
					var text = $('#answer label[for="' + elem.id + '"]').text();
					var newColumn = $(edbUiTags.itemDiv).append(text);
					var targetSelector = ($(elem).hasClass('relKeys')) ? '.keys' : '.items';
					var target = $(droppableContainer + ' .container:eq(' + thisRelation + ') ' + targetSelector);
					newColumn.data({checkboxId: elem.id});

					addColumnToRelation(newColumn, target);
					
					if (thisRelation != relation) {
						handleEnsureLastEmptyRelation();
					}
					relation = thisRelation;
				});
			};
			
			var readRelString = function(elem) {
				var classes = $(elem).attr('className').match(/rel\d+/);
				return (classes) ? parseInt(classes[0].substr(3), 10) : -1; 
			};
			
			/*** Submit ***/
			var formKeysAttributes = function(){
				if ($('input[name=normalform]:checked', this).val() == 'true') {
					// User accepted table as normalized
					return true;
				}
				return true;
			};
			
			console.log("Detecting form-Type ...");
			if ($('form.columns').length > 0) {
				console.log("Formtype 'columns' detected.");
			} else if($('form.keysAttributes').length > 0) {
				console.log("Formtype 'KeysAttributes' detected.");
				$('.exercise:not(.modify) td, .exercise:not(.modify) th').draggable(draggableConfig);
				readFormDataToUI();
				$(droppablesSelector).droppable(droppableConfig);
				//$('form.keysAttributes').submit(formKeysAttributes);
			}
			
			

		});



/*** Others ***/
function popUpWin (url, win, width, height, options) {
	var leftPos = ((screen.availWidth - width) / 2);
	var topPos = ((screen.availHeight - height) / 2);
	options += 'width=' + width + ',height=' + height + ',left=' + leftPos + ',top=' + topPos;
	window.open(url, win, options);
}

function openHelp(appId,sid) {
	popUpWin('/hilfe.jsp;jsessionid=' + sid + '?appid=' + appId, 'Hilfe', 600, 400, 'menubar=no, status=no, scrollbars=yes, toolbar=no, location=no,');
}

function openExplication(frageId,sid) {
	popUpWin('/erklaerungen.jsp;jsessionid=' + sid + '?frageId=' + frageId, 'erklaerungen', 600, 400, 'menubar=no, status=no, scrollbars=yes, toolbar=no, location=no,');
	//settimeout('erklaerungen.focus()',300);
}


function social_mo(name) {
	if (name == '') { name = '...'; } else { name='&nbsp;<b>'+name+'</b>'; }
	document.getElementById('social_motext').innerHTML=name;
}
function social_click(name) {
	URL = encodeURIComponent('http://edb.gm.fh-koeln.de');
	Title = encodeURIComponent(document.title);
	switch(name) {	
		case 'blinklist': 		window.open('http://www.blinklist.com/index.php?Action=Blink/addblink.php&Description=&Url='+URL+'&Title='+Title); break;
		case 'delicious': 		window.open('http://del.icio.us/post?url='+URL+'&title='+Title); break;
		case 'google': 			window.open('http://www.google.com/bookmarks/mark?op=add&hl=de&bkmk='+URL+'&title='+Title); break;
		case 'folkd': 			window.open('http://www.folkd.com/submit/page/'+URL); break;
		case 'furl': 			window.open('http://www.furl.net/storeIt.jsp?u='+URL+'&t='+Title); break;
		case 'linkarena':		window.open('http://linkarena.com/bookmarks/addlink/?url='+URL+'&title='+Title+'&desc=&tags=');	break;
		case 'oneview':			window.open('http://beta.oneview.de:80/quickadd/neu/addBookmark.jsf?URL='+URL+'&title='+Title);	break;
		case 'tausendreporter': window.open('http://tausendreporter.stern.de/submit.php?url='+URL); break;
		case 'technorati': 		window.open('http://technorati.com/faves?add='+URL); break;
		case 'webnews': 		window.open('http://www.webnews.de/einstellen?url='+URL+'&title='+Title); break;
		case 'wong': 			window.open('http://www.mister-wong.de/index.php?action=addurl&bm_url='+URL+'&bm_description='+Title); break;
		case 'yahoo':			window.open('http://myweb2.search.yahoo.com/myresults/bookmarklet?u='+URL+'&t='+Title);	break;
		case 'yigg': 			window.open('http://yigg.de/neu?exturl='+URL+'&exttitle='+Title); break;	
	}
}
