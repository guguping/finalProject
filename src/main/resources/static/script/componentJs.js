const homeImg = document.querySelector('.boardMain-nav-list-home-img');
const homeLogoImg = document.querySelector('.boardMain-nav-list-home-logo-img');
const searchImg = document.querySelector('.boardMain-nav-list-search-box-size');
const searchLogoImg = document.querySelector('.boardMain-nav-list-search-img');
const reelsImg = document.querySelector('.boardMain-nav-list-reels-box-size');
const reelsLogoImg = document.querySelector('.boardMain-nav-list-reels-img');
const directImg = document.querySelector('.boardMain-nav-list-Direct-box-size');
const directLogoImg = document.querySelector('.boardMain-nav-list-Direct-img');
const newBoardImg = document.querySelector('.boardMain-nav-list-newBoard-box-size');
const newBoardLogoImg = document.querySelector('.boardMain-nav-list-newBoard-img');
const profileImg = document.querySelector('.boardMain-nav-list-profile-box-size');
const profileLogoImg = document.querySelector('.boardMain-nav-list-profile-img');
const plusImg = document.querySelector('.boardMain-nav-list-plus-list-box-size');
const plusLogoImg = document.querySelector('.boardMain-nav-list-plus-img');
const prevButton = document.getElementById('prevButton');
const nextButton = document.getElementById('nextButton');
const pageNumberContainer = document.getElementById('previewImgPagingItem-num-item-box');

homeImg.addEventListener('mouseover', function () {
    homeImg.style.backgroundColor = 'rgba(219, 219, 219, 0.5)';
    homeImg.style.borderRadius = '10px';
    homeLogoImg.style.transform = 'scale(1.05)';
});

homeImg.addEventListener('mouseout', function () {
    homeImg.style.backgroundColor = '';
    homeImg.style.borderRadius = '';
    homeLogoImg.style.transform = '';
});

searchImg.addEventListener('mouseover', function () {
    searchImg.style.backgroundColor = 'rgba(219, 219, 219, 0.5)';
    searchImg.style.borderRadius = '10px';
    searchLogoImg.style.transform = 'scale(1.05)';
});

searchImg.addEventListener('mouseout', function () {
    searchImg.style.backgroundColor = '';
    searchImg.style.borderRadius = '';
    searchLogoImg.style.transform = '';
});
reelsImg.addEventListener('mouseover', function () {
    reelsImg.style.backgroundColor = 'rgba(219, 219, 219, 0.5)';
    reelsImg.style.borderRadius = '10px';
    reelsLogoImg.style.transform = 'scale(1.05)';
});

reelsImg.addEventListener('mouseout', function () {
    reelsImg.style.backgroundColor = '';
    reelsImg.style.borderRadius = '';
    reelsLogoImg.style.transform = '';
});
directImg.addEventListener('mouseover', function () {
    directImg.style.backgroundColor = 'rgba(219, 219, 219, 0.5)';
    directImg.style.borderRadius = '10px';
    directLogoImg.style.transform = 'scale(1.05)';
});

directImg.addEventListener('mouseout', function () {
    directImg.style.backgroundColor = '';
    directImg.style.borderRadius = '';
    directLogoImg.style.transform = '';
});
newBoardImg.addEventListener('mouseover', function () {
    newBoardImg.style.backgroundColor = 'rgba(219, 219, 219, 0.5)';
    newBoardImg.style.borderRadius = '10px';
    newBoardLogoImg.style.transform = 'scale(1.05)';
});

newBoardImg.addEventListener('mouseout', function () {
    newBoardImg.style.backgroundColor = '';
    newBoardImg.style.borderRadius = '';
    newBoardLogoImg.style.transform = '';
});
profileImg.addEventListener('mouseover', function () {
    profileImg.style.backgroundColor = 'rgba(219, 219, 219, 0.5)';
    profileImg.style.borderRadius = '10px';
    profileLogoImg.style.transform = 'scale(1.05)';
});

profileImg.addEventListener('mouseout', function () {
    profileImg.style.backgroundColor = '';
    profileImg.style.borderRadius = '';
    profileLogoImg.style.transform = '';
});
plusImg.addEventListener('mouseover', function () {
    plusImg.style.backgroundColor = 'rgba(219, 219, 219, 0.5)';
    plusImg.style.borderRadius = '10px';
    plusLogoImg.style.transform = 'scale(1.05)';
});

plusImg.addEventListener('mouseout', function () {
    plusImg.style.backgroundColor = '';
    plusImg.style.borderRadius = '';
    plusLogoImg.style.transform = '';
});

const fileUpload = () => {
    const uploadInput = document.getElementById('img-input-');
    uploadInput.click();
}
const boardSave = () => {
    const boardSave = document.getElementById('boardSave-btn');
    boardSave.click();
}
const saveFormOn = () => {
    const boardSaveLayout = document.getElementById('boardSave-layout');
    boardSaveLayout.style.display = "block";
}
const saveFormOff = () => {
    const boardSaveLayout = document.getElementById('boardSave-layout');
    const previewImageContainer = document.getElementById("board-save-form-main-img");
    const inputFile = document.getElementById('img-input-');
    const result = "<svg aria-label=\"이미지나 동영상과 같은 미디어를 나타내는 아이콘\"\n" +
        "                                                                                                             class=\"board-save-form-svg\"\n" +
        "                                                                                                             color=\"rgb(0, 0, 0)\"\n" +
        "                                                                                                             fill=\"rgb(0, 0, 0)\"\n" +
        "                                                                                                             height=\"77\"\n" +
        "                                                                                                             role=\"img\"\n" +
        "                                                                                                             viewBox=\"0 0 97.6 77.3\"\n" +
        "                                                                                                             width=\"96\">\n" +
        "                                                                                                            <title>이미지나\n" +
        "                                                                                                                동영상과 같은\n" +
        "                                                                                                                미디어를\n" +
        "                                                                                                                나타내는\n" +
        "                                                                                                                아이콘</title>\n" +
        "                                                                                                            <path d=\"M16.3 24h.3c2.8-.2 4.9-2.6 4.8-5.4-.2-2.8-2.6-4.9-5.4-4.8s-4.9 2.6-4.8 5.4c.1 2.7 2.4 4.8 5.1 4.8zm-2.4-7.2c.5-.6 1.3-1 2.1-1h.2c1.7 0 3.1 1.4 3.1 3.1 0 1.7-1.4 3.1-3.1 3.1-1.7 0-3.1-1.4-3.1-3.1 0-.8.3-1.5.8-2.1z\"\n" +
        "                                                                                                                  fill=\"currentColor\"></path>\n" +
        "                                                                                                            <path d=\"M84.7 18.4 58 16.9l-.2-3c-.3-5.7-5.2-10.1-11-9.8L12.9 6c-5.7.3-10.1 5.3-9.8 11L5 51v.8c.7 5.2 5.1 9.1 10.3 9.1h.6l21.7-1.2v.6c-.3 5.7 4 10.7 9.8 11l34 2h.6c5.5 0 10.1-4.3 10.4-9.8l2-34c.4-5.8-4-10.7-9.7-11.1zM7.2 10.8C8.7 9.1 10.8 8.1 13 8l34-1.9c4.6-.3 8.6 3.3 8.9 7.9l.2 2.8-5.3-.3c-5.7-.3-10.7 4-11 9.8l-.6 9.5-9.5 10.7c-.2.3-.6.4-1 .5-.4 0-.7-.1-1-.4l-7.8-7c-1.4-1.3-3.5-1.1-4.8.3L7 49 5.2 17c-.2-2.3.6-4.5 2-6.2zm8.7 48c-4.3.2-8.1-2.8-8.8-7.1l9.4-10.5c.2-.3.6-.4 1-.5.4 0 .7.1 1 .4l7.8 7c.7.6 1.6.9 2.5.9.9 0 1.7-.5 2.3-1.1l7.8-8.8-1.1 18.6-21.9 1.1zm76.5-29.5-2 34c-.3 4.6-4.3 8.2-8.9 7.9l-34-2c-4.6-.3-8.2-4.3-7.9-8.9l2-34c.3-4.4 3.9-7.9 8.4-7.9h.5l34 2c4.7.3 8.2 4.3 7.9 8.9z\"\n" +
        "                                                                                                                  fill=\"currentColor\"></path>\n" +
        "                                                                                                            <path d=\"M78.2 41.6 61.3 30.5c-2.1-1.4-4.9-.8-6.2 1.3-.4.7-.7 1.4-.7 2.2l-1.2 20.1c-.1 2.5 1.7 4.6 4.2 4.8h.3c.7 0 1.4-.2 2-.5l18-9c2.2-1.1 3.1-3.8 2-6-.4-.7-.9-1.3-1.5-1.8zm-1.4 6-18 9c-.4.2-.8.3-1.3.3-.4 0-.9-.2-1.2-.4-.7-.5-1.2-1.3-1.1-2.2l1.2-20.1c.1-.9.6-1.7 1.4-2.1.8-.4 1.7-.3 2.5.1L77 43.3c1.2.8 1.5 2.3.7 3.4-.2.4-.5.7-.9.9z\"\n" +
        "                                                                                                                  fill=\"currentColor\"></path>\n" +
        "                                                                                                        </svg>\n" +
        "                                                                                                        <div class=\"tkwlsrhkehddudtkd\">\n" +
        "                                                                                                            <span class=\"tkwlsrhkehddudtkd-text\">\n" +
        "                                                                                                                사진과 동영상을 여기에 끌어다 놓으세요\n" +
        "                                                                                                            </span>\n" +
        "                                                                                                        </div>\n" +
        "                                                                                                        <div class=\"tkwlsrhkehddudtkd-btn-box\">\n" +
        "                                                                                                            <div class=\"tkwlsrhkehddudtkd-btn-box-inner\">\n" +
        "                                                                                                                <button class=\"img-upload-btn\"\n" +
        "                                                                                                                        id=\"img-upload-btn\"\n" +
        "                                                                                                                        type=\"button\"\n" +
        "                                                                                                                        onclick=\"fileUpload()\">\n" +
        "                                                                                                                    컴퓨터에서\n" +
        "                                                                                                                    선택\n" +
        "                                                                                                                </button>\n" +
        "                                                                                                            </div>\n" +
        "                                                                                                        </div>"
    previewImageContainer.innerHTML = "";
    previewImageContainer.innerHTML = result;
    inputFile.value = '';
    boardSaveLayout.style.display = "none";
    previewImaPagingBox.style.display = "none";
    prevButton.style.display = "none";
    nextButton.style.display = "block";
    pageNumberContainer.innerHTML = "";
}

function readImages(input) {
    if (input.files && input.files.length > 0) {
        const previewImageContainer = document.getElementById("board-save-form-main-img");
        const previewImaPagingBox = document.getElementById('previewImaPagingBox');
        const itemsPerPage = 1; // 페이지당 보여줄 이미지 수
        let currentPage = 1; // 현재 페이지
        const totalPages = Math.ceil(input.files.length / itemsPerPage);

        previewImageContainer.innerHTML = "";
        for (let i = 0; i < input.files.length; i++) {
            const reader = new FileReader();
            reader.onload = (function (fileIndex) {
                return function (e) {
                    const previewImage = document.createElement("img");
                    previewImage.src = e.target.result;
                    previewImage.style.maxWidth = "718px";
                    previewImage.style.maxHeight = "718px";
                    previewImage.className = "imgxormdlqslek";
                    if (fileIndex >= itemsPerPage) {
                        previewImage.style.display = "none";
                    }
                    previewImageContainer.appendChild(previewImage);
                };
            })(i);
            reader.readAsDataURL(input.files[i]);
        }

        if (input.files.length > itemsPerPage) {
            previewImaPagingBox.style.display = "block";
            pageNumberContainer.innerHTML = "";
            for (let i = 1; i <= totalPages; i++) {
                const pageButton = document.createElement("div");
                pageButton.className = "previewImgPagingItem-num-item-on"
                if (i === currentPage) {
                    pageButton.className = "previewImgPagingItem-num-item-off";
                }
                pageNumberContainer.style.display = "flex";
                pageNumberContainer.appendChild(pageButton);
            }
        }



        function showPage(pageNumber) {
            const images = previewImageContainer.getElementsByTagName('img');
            const startIndex = (pageNumber - 1) * itemsPerPage;
            const endIndex = startIndex + itemsPerPage;

            for (let i = 0; i < images.length; i++) {
                if (i >= startIndex && i < endIndex) {
                    images[i].style.display = "block";
                } else {
                    images[i].style.display = "none";
                }
            }

            if (pageNumber === 1) {
                prevButton.style.display = "none";
            } else {
                prevButton.style.display = "block";
            }

            // 다음 버튼 디스플레이 설정
            if (pageNumber === totalPages) {
                nextButton.style.display = "none";
            } else {
                nextButton.style.display = "block";
            }
            if (input.files.length > itemsPerPage) {
                previewImaPagingBox.style.display = "block";
                pageNumberContainer.innerHTML = "";
                for (let i = 1; i <= totalPages; i++) {
                    const pageButton = document.createElement("div");
                    pageButton.className = "previewImgPagingItem-num-item-on"
                    if (i === pageNumber) {
                        pageButton.className = "previewImgPagingItem-num-item-off";
                    }
                    pageNumberContainer.style.display = "flex";
                    pageNumberContainer.appendChild(pageButton);
                }
            }
        }
        function goToPrevPage() {
            if (currentPage > 1) {
                currentPage--;
                showPage(currentPage);
            }
        }

        function goToNextPage() {
            if (currentPage < totalPages) {
                currentPage++;
                showPage(currentPage);
            }
        }

        prevButton.addEventListener("click", goToPrevPage);
        nextButton.addEventListener("click", goToNextPage);
    }
}

const memberFile = document.getElementById("img-input-");
memberFile.addEventListener("change", e => {
    readImages(e.target);
});

const image_check = () => {
    const imageOk = document.getElementById("img-input-").value;
    if (imageOk == "") {
        console.log("널");
        alert("이미지를 선택하십시오");
        return false;
    } else {
        console.log("널 아님");
        return true;
    }
}
