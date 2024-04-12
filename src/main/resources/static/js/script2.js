function loadinganimation(){
    gsap.from("#main #hero h1",{
    y:100,
    opacity:0,
    delay:0.5,
    duration:0.9,
    // stagger:0.3
})
}
loadinganimation()

function loadingimg1(){
    gsap.from("#course1 img",{
    y:100,
    opacity:0,
    delay:0.5,
    duration:0.9,
    // stagger:0.3
})
}
loadingimg1()

function loadingimg2(){
    gsap.from("#course2 img",{
    y:100,
    opacity:0,
    delay:0.5,
    duration:0.9,
    // stagger:0.3
})
}
loadingimg2()

function loadingimg3(){
    gsap.from("#course3 img",{
    y:100,
    opacity:0,
    delay:0.5,
    duration:0.9,
    // stagger:0.3
})
}
loadingimg3()

// Get reference to the search field and form
const searchField = document.getElementById('searchField');
const searchForm = document.getElementById('searchForm');

// Add keyup event listener to the search field
searchField.addEventListener('keyup', function(event) {
    // Check if the Enter key (key code 13) was pressed
    if (event.keyCode === 13) {
        // Prevent the default form submission
        event.preventDefault();
        
        // Submit the form
        searchForm.submit();
    }
});
