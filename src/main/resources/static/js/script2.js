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

function checkEnterKey(event) {
    if (event.key === 'Enter') {
        performSearch();
    }
}

function performSearch() {
    var jobField = document.getElementById('jobField').value;

    // Make an AJAX request to your Spring API
    fetch('/search/' + jobField)
        .then(response => response.json())
        .then(data => displayResults(data))
        .catch(error => console.error('Error:', error));
}

function displayResults(results) {
    var resultsContainer = document.getElementById('results');
    resultsContainer.innerHTML = '';

    if (results.length === 0) {
        resultsContainer.innerHTML = '<p>No results found.</p>';
    } else {
        resultsContainer.style.opacity = 0; // Set initial opacity to 0

        gsap.to(resultsContainer, {
            opacity: 1,
            duration: 0.5,
            onComplete: function () {
                // Animation complete, now append results
                results.forEach(result => {
                    // Assuming jobField is a property in each result object
                    var jobField = result.jobField; // Replace 'jobField' with the actual property name

                    // Display jobField in a paragraph
                    resultsContainer.innerHTML += '<p class="result" onclick="serveFile(\'' + jobField + '\')">' + jobField + '</p>';
                });
            }
        });
    }
}

function serveFile(pdfName) {
    // Make an AJAX request to serve the PDF file
    window.location.href = '/file/' + pdfName + ".pdf";
}









