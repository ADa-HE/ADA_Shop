/*price range*/

$('#sl2').slider();

var RGBChange = function () {
    $('#RGB').css('background', 'rgb(' + r.getValue() + ',' + g.getValue() + ',' + b.getValue() + ')')
};

/*scroll to top*/

$(document).ready(function () {
    $(function () {
        $.scrollUp({
            scrollName: 'scrollUp', // Element ID
            scrollDistance: 300, // Distance from top/bottom before showing element (px)
            scrollFrom: 'top', // 'top' or 'bottom'
            scrollSpeed: 300, // Speed back to top (ms)
            easingType: 'linear', // Scroll to top easing (see http://easings.net/)
            animation: 'fade', // Fade, slide, none
            animationSpeed: 200, // Animation in speed (ms)
            scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
            //scrollTarget: false, // Set a custom target element for scrolling to the top
            scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
            scrollTitle: false, // Set a custom <a> title if required.
            scrollImg: false, // Set true to use image
            activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
            zIndex: 2147483647 // Z-Index for the overlay
        });
    });
  
});
//  function pagger(id, pageindex, totalpage, gap)
//    {
//        var container = document.getElementById(id);
//        var result = '';
//        result = pageindex + ' ' + totalpage + ' ' + gap;
////    if(pageindex - gap > 1)
////        result+= '<a href="list?page=1">' + 'First' + '</a>';
////    
////    for(var i=pageindex-gap;i<pageindex;i++)
////        if(i>0)
////            result+= '<a href="list?page='+i+'">' + i + '</a>';
////    
////    result+= '<span>' + pageindex + '</span>';
////    
////    for(var i=pageindex+1;i<=pageindex + gap;i++)
////        if(i<=totalpage)
////            result+= '<a href="list?page='+i+'">' + i + '</a>';
////    
////    if(pageindex + gap < totalpage)
////        result+= '<a href="list?page='+totalpage+'">' + 'Last' + '</a>';
//
//        container.innerHTML = result;
//    }