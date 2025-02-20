const backToTopButton = document.getElementById("backToTop");

// Show the button when the user scrolls down 100px from the top
window.onscroll = function () {
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        backToTopButton.style.display = "block"; // Show the button
    } else {
        backToTopButton.style.display = "none"; // Hide the button
    }
};

// Scroll to the top when the user clicks the button
backToTopButton.onclick = function () {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'  // Smooth scroll effect
    });
};
