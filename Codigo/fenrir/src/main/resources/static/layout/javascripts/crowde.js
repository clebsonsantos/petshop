var CW = CW || {};

CW.onSidebarToggleRequest = function(event) {
  event.preventDefault();
  $(this).blur();

  $('.js-sidebar, .js-content').toggleClass('is-toggled');
};

CW.onSearchModalShowRequest = function(event) {
  event.preventDefault();

  $('.js-search-modal').fadeIn('slow');
  $('body').addClass('cw-no-scroll');
  
  $('.js-search-modal-input').val('').select();
  
};

CW.onSearchModalCloseRequest = function(event) {
  event.preventDefault();

  $('.js-search-modal').hide();
  $('body').removeClass('cw-no-scroll');
};

//CW.onFormLoadingSubmit = function(event) {
  //event.preventDefault();

  //CW.showLoadingComponent();

  //setTimeout(function() {
  //  CW.hideLoadingComponent();
  //}, 2000);
//};

CW.showLoadingComponent = function() {
  $('.js-loading-overlay').css('display', 'table').hide().fadeIn('slow');
};

CW.hideLoadingComponent = function() {
  $('.js-loading-component').fadeOut('fast');
};

CW.initStickyTableHeaders = function() {
  if ($(window).width() >= 992) { 
    var stickyRef = $('.js-sticky-reference');
    var stickyTable = $('.js-sticky-table');

    if (stickyRef && stickyTable) {
      stickyTable.stickyTableHeaders({fixedOffset: stickyRef});
    }
  }
};

CW.onMenuGroupClick = function(event) {
  var subItems = $(this).parent().find('ul');

  if (subItems.length) {
    event.preventDefault();
    $(this).parent().toggleClass('is-expanded');
  }
};

CW.initMenu = function() {
  $('.js-menu > ul > li > a').bind('click', CW.onMenuGroupClick);
  $('.cw-menu__item .is-active').parents('.cw-menu__item').addClass('is-expanded is-active');
};

$(function() {
  if (CW.init) {
    CW.init();
  }

  CW.initMenu();
  CW.initStickyTableHeaders();
  
  // Enable Bootstrap tooltip
  $('.js-tooltip').tooltip();
  
  // Bind events
  $('.js-sidebar-toggle').bind('click', CW.onSidebarToggleRequest);
  $('.js-search-modal-trigger-show').bind('click', CW.onSearchModalShowRequest);
  $('.js-search-modal-close').bind('click', CW.onSearchModalCloseRequest);
  //$('.js-form-loading').bind('submit', CW.onFormLoadingSubmit);
});