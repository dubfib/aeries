const interval = setInterval(() => {
    const button = document.getElementById("LoginButton");

    if (button) {
        clearInterval(interval);

        const div = document.createElement("div");
        div.id = "dynamic-loading";
        div.style.position = "fixed";
        div.style.top = "50%";
        div.style.left = "50%";
        div.style.transform = "translate(-50%, -50%)";
        div.style.zIndex = "9999";
        div.style.display = "none";
        div.style.fontSize = "24px";
        div.style.color = "black";
        div.style.backgroundColor = "white";
        div.style.padding = "10px";
        div.style.border = "1px solid black";
        div.style.borderRadius = "5px";
        div.innerText = "Loading...";
        document.body.appendChild(div);

        button.addEventListener("click", () => div.style.display = "block");
    };
}, 100);