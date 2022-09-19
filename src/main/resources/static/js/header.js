document.addEventListener("DOMContentLoaded", function(){
  	window.addEventListener('scroll', function() {
      if (window.scrollY > 50) {
        document.getElementById('navbar_top').classList.add('fixed-top');
        // add padding top to show content behind navbar
        navbar_height = document.querySelector('.navbar').offsetHeight;
        document.body.style.paddingTop = navbar_height + 'px';
      } else {
        document.getElementById('navbar_top').classList.remove('fixed-top');
         // remove padding top from body
        document.body.style.paddingTop = '0';
      }
  });
});

function incrementValue(id)
{
    var value = parseInt(document.getElementById('number'+id).value, 10);
    value = isNaN(value) ? 0 : value;
    value++;
    document.getElementById('number'+id).value = value;
}
function decrementValue(id)
{
    console.log(id);
    var value = parseInt(document.getElementById('number'+id).value, 10);
    value = isNaN(value) ? 0 : value;
    value--;
    if(value<0){
    value = 0}
    document.getElementById('number'+id).value = value;
}

