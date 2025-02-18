import { domainPort, serverDomain, membersInfo } from "./info.js"


document.getElementById("logo-container").href = domainPort + "/" + serverDomain + "/";

const headerContainer = document.getElementById("member-group-info")
membersInfo.forEach(member => {
    const teamMember = document.createElement("div");
    teamMember.innerHTML = `
        <div class="team-member">${member?.group} ${member?.name}</div>
    `;
    headerContainer.appendChild(teamMember);
});

const container = document.getElementById("scroll-container");

membersInfo.forEach(member => {
    const memberItem = document.createElement("div");
    memberItem.classList.add("member-item");
    memberItem.innerHTML = `
        <div class="account-img-circle">
            <img class="account-img" src="${member?.githubAvatar ? member?.githubAvatar : "images/account-icon.png"}" alt="Account">
        </div>
        <div class="member-info">
            <div class="member-name">${member?.name}</div>
            <div class="member-group">${member?.group}</div>
            <a class="member-links" href="${member?.github}">GitHub</a>
        </div>
        <a class="visit-button" href="${"/" + serverDomain + member?.pageUrl}">More</a>
    `;

    container.appendChild(memberItem);
});



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
