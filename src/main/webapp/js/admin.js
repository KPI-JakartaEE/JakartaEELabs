function addKeyword(containerId) {
    let container = document.getElementById("keywords-container-" + containerId);

    let label = document.createElement("label");
    label.classList.add("keywords-input-wrapper");

    let input = document.createElement("input");
    input.type = "text";
    input.name = "keywords";
    input.placeholder = "Ключове слово";
    input.required = true;

    let trashIcon = document.createElement("img");
    trashIcon.classList.add("trash-icon");
    trashIcon.src = `/images/trash-icon.png`;
    trashIcon.alt = "trash-icon";

    trashIcon.onclick = function() {
        deleteKeyword(trashIcon);
    };

    label.appendChild(input);
    label.appendChild(trashIcon);

    container.appendChild(label);
}

function deleteKeyword(trashIcon) {
    const label = trashIcon.closest('label');

    if (label) {
        label.remove();
    }
}