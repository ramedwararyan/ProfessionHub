document.addEventListener("DOMContentLoaded", function () {
    // Wait for the document to be fully loaded

    // Find the form and submit button by their IDs
    var questionForm = document.getElementById("question_form");
    var submitButton = document.getElementById("submit_button");

    // Add a click event listener to the submit button
    submitButton.addEventListener("click", function () {
        // Call the submitForm function when the button is clicked
        submitForm();
    });

    // Function to submit the form
    function submitForm() {
        // Perform any additional form validation or processing here if needed

        // Submit the form
        questionForm.submit();
        return true;
    }
});
