function upDate(previewPic) {
    /* Here changing the url for the background image of the div with the id = "image"
       to the source file of the preview image and change the text  of the div with the id = "image"
       to the alt text of the preview image
       */
    $('#image').css('background-image', 'url('+previewPic.src+ ')');
    $('#image').text(previewPic.alt);

}

function unDo() {
    /* Here updating the url for the background image of the div with the id = "image"
   back to the orginal-image.  You can use the css code to see what that original URL was and Change the text  of the
   div with the id = "image" back to the original text.
   */
    $('#image').text("Hover over an image below to display here.");
    $('#image').css('background-image', 'none');

}