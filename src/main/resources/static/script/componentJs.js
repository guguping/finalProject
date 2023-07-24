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
const navLogoutBtn = document.getElementById('nav-logout-btn-');
const navLogoutBtnHover = document.getElementById('nav-logout-btn-hover-box');
const darkModG = document.getElementById('dakrModG');
const darkModGHover = document.getElementById('darkModG-Hover');

const plusNavDisplayMain = document.getElementById('plusNavDisplay-main');

const plusBoardOpModal = document.getElementById('plus-modal-board-');


function openBoardMenu(boardId) {
    let plusBoardOpModalBtn2 = document.getElementById("board-modal-on-off" + boardId);
    if (plusBoardOpModal.style.display === "none") {
        plusBoardOpModal.style.display = "block";
        console.log("응애");
    } else {
        plusBoardOpModal.style.display = "none";
    }
    console.log("Performing post menu operation for boardId:", boardId);
}

const plusNavDisplay = () => {
    if (plusNavDisplayMain.style.display === "none") {
        plusNavDisplayMain.style.display = "block";
    } else {
        plusNavDisplayMain.style.display = "none";
    }
}

darkModG.addEventListener("mouseover", function () {
    darkModGHover.style.opacity = "1";
})

darkModG.addEventListener("mouseout", function () {
    darkModGHover.style.opacity = "0";
})

navLogoutBtn.addEventListener("mouseover", function () {
    navLogoutBtnHover.style.opacity = "1";
})

navLogoutBtn.addEventListener("mouseout", function () {
    navLogoutBtnHover.style.opacity = "0";
})

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
var boardMainContainers = document.querySelectorAll(".boardMain-main-board-img-box"); // 게시물 컨테이너들 선택

// 각 게시물 컨테이너에 대해 이미지 페이징 초기화
boardMainContainers.forEach(function (boardMainContainer) {
    var imageContainer = boardMainContainer.querySelector("#imageContainer"); // 이미지 컨테이너 선택
    var imageElements; // 이미지 요소들
    var currentPage = 1; // 초기 페이지 값
    var imagesPerPage = 1; // 한 페이지에 표시할 이미지 개수

    // 이미지 페이징을 초기화하는 함수
    function initImagePaging() {
        // 이미지 요소들 가져오기
        imageElements = imageContainer.querySelectorAll("img");

        // 첫 페이지 이미지 표시
        showPage(currentPage);

        // 이전 버튼 클릭 시 이벤트 처리
        var previousButton = boardMainContainer.querySelector(".boardMain-board-paging-left");
        previousButton.addEventListener("click", goToPreviousPage);

        // 다음 버튼 클릭 시 이벤트 처리
        var nextButton = boardMainContainer.querySelector(".boardMain-board-paging-right");
        nextButton.addEventListener("click", goToNextPage);
    }

    // 페이지 번호에 따라 이미지를 보여주는 함수
    function showPage(pageNumber) {
        var startIndex = (pageNumber - 1) * imagesPerPage;
        var endIndex = startIndex + imagesPerPage;

        for (var i = 0; i < imageElements.length; i++) {
            if (i >= startIndex && i < endIndex) {
                imageElements[i].style.display = "block"; // 이미지 보이기
            } else {
                imageElements[i].style.display = "none"; // 이미지 숨기기
            }
        }

        // 페이지 번호 업데이트
        updatePageNumbers(pageNumber);
    }

    // 이전 페이지로 이동하는 함수
    function goToPreviousPage() {
        if (currentPage > 1) {
            currentPage--;
            showPage(currentPage);
        }
    }

    // 다음 페이지로 이동하는 함수
    function goToNextPage() {
        if (currentPage < Math.ceil(imageElements.length / imagesPerPage)) {
            currentPage++;
            showPage(currentPage);
        }
    }

    // 페이지 번호 업데이트 함수
    function updatePageNumbers(currentPage) {
        var pagingNumContainer = boardMainContainer.querySelector(".boardMain-board-paging-num");
        var pagingNumElements = pagingNumContainer.getElementsByClassName("boardMain-board-paging-num-on");

        // 기존 페이지 번호 제거
        while (pagingNumElements.length > 0) {
            pagingNumElements[0].remove();
        }

        // 새로운 페이지 번호 추가
        for (var i = 1; i <= Math.ceil(imageElements.length / imagesPerPage); i++) {
            var pageNumElement = document.createElement("div");
            pageNumElement.classList.add("boardMain-board-paging-num-on");
            pageNumElement.innerText = i;
            pagingNumContainer.appendChild(pageNumElement);

            // 현재 페이지 표시
            if (i === currentPage) {
                pageNumElement.classList.add("active");
            }

            // 페이지 번호 클릭 시 해당 페이지로 이동
            pageNumElement.addEventListener("click", function () {
                var pageNum = parseInt(this.innerText);
                showPage(pageNum);
            });
        }
    }

    // 페이지 로드 시 이미지 페이징 초기화
    window.addEventListener("load", initImagePaging);
});


const comment_list = (commentList, memberNickname, boardId) => {
    console.log("댓글 목록 함수", commentList);
    const resultArea = document.getElementById('comment-list' + boardId);
    resultArea.innerHTML = '';

    const latestComments = commentList.slice(0, 1);

    for (let i in latestComments) {
        const commentHtml = `
            <div class="board-contents-comment-small1">
                <div class="board-contents-comment-box">
                    <div style="display: inline;">
                        <div style="display: inline;">
                            <a href="#" class="board-contents-writer">
                                <span class="djelRKwlemfdjrk">
                                    <div class="wkrtjdwkdlqfur">${memberNickname}</div>
                                </span>
                            </a>
                        </div>
                    </div>
                    <span class="board-contents-margin"> </span>
                    <span class="board-contents-text-contents">
                        <span class="board-contents-text-contents-inner">${latestComments[i].commentContents}</span>
                    </span>
                </div>
            </div>
        `;
        resultArea.innerHTML += commentHtml;
    }
};

const comment_write = (boardId, memberId) => {
    const contents = document.getElementById('commentContents' + boardId).value;
    console.log("contents = " + contents);
    axios({
        method: "post",
        url: "/comment/save",
        data: {
            commentContents: contents,
            boardId: boardId,
            memberId: memberId
        }
    }).then(res => {
        const boardMain = res.data;
        const memberDTO = boardMain.memberDTO;
        const commentList = boardMain.commentDTOList;
        console.log("res", res);
        console.log("댓글 목록", res.data);
        // document.querySelector("#commentContents").value = "";
        document.getElementById('commentContents' + boardId).value = "";

        // 멤버 닉네임 설정
        const memberNickname = memberDTO.memberNickName;

        comment_list(commentList, memberNickname, boardId);
    }).catch(err => {
        console.log("err", err);
        alert("실패");
    });
};
const boardDelete = (id) => {
    console.log("boardid =" + id);
    const boardid = id
    axios({
        method: "delete",
        url: "/board/" + boardid
    }).then(res => {
        location.href = "/board/main";
    }).catch(err => {
        alert("삭제 실패!!");
    });
}


var currentPage = 1; // 현재 페이지 초기값을 설정합니다.
var imagesPerPage = 1; // 한 페이지에 표시할 이미지 수를 설정합니다.
// var previousButton = document.querySelector(".boardMain-board-paging-left");
// previousButton.style.display = "none";
function goToPreviousPage(boardId) {
    console.log("boardId=" + boardId);
    var startIndex = (currentPage - 1) * imagesPerPage;
    var endIndex = startIndex + imagesPerPage;
    var boardMainContainer = document.getElementById("boardMain-main-board-img-box" + boardId);
    var imageElements = boardMainContainer.querySelectorAll("img");

    for (var i = 0; i < imageElements.length; i++) {
        if (i >= startIndex && i < endIndex) {
            imageElements[i].style.display = "block"; // set the image element visible
        } else {
            imageElements[i].style.display = "none"; // Set the image element to be hidden
        }
    }
    if (currentPage > 1) {
        currentPage--;
    }
    const nextButton = document.getElementById("boardMain-board-paging-right" + boardId);
    const previousButton = document.getElementById("boardMain-board-paging-left" + boardId);

    if (currentPage === 1) {
        nextButton.style.display = "block";
        previousButton.style.display = "none";
    } else {
        nextButton.style.display = "block";
        previousButton.style.display = "block";
    }
    if (imageElements.length === 1) {
        nextButton.style.display = "none";
        previousButton.style.display = "none";
    }
}


function goToNextPage(boardId) {
    console.log("boardId=" + boardId);
    var boardMainContainer = document.getElementById("boardMain-main-board-img-box" + boardId);
    var imageElements = boardMainContainer.querySelectorAll("img");
    const startIndex = (currentPage - 1) * imagesPerPage;
    const endIndex = startIndex + imagesPerPage;


    for (let i = 0; i < imageElements.length; i++) {
        if (i >= startIndex && i < endIndex) {
            imageElements[i].style.display = "block"; // set the image element visible
        } else {
            imageElements[i].style.display = "none"; // Set the image element to be hidden
        }
    }

    if (currentPage < Math.ceil(imageElements.length / imagesPerPage)) {
        currentPage++;
    }
    const nextButton = document.getElementById("boardMain-board-paging-right" + boardId);
    const previousButton = document.getElementById("boardMain-board-paging-left" + boardId);

    if (currentPage === Math.ceil(imageElements.length / imagesPerPage)) {
        nextButton.style.display = "none";
        previousButton.style.display = "block";
    } else {
        nextButton.style.display = "block";
        previousButton.style.display = "block";
    }
    if (imageElements.length === 1) {
        nextButton.style.display = "none";
        previousButton.style.display = "none";
    }
}

var boardMainContainer2 = document.querySelectorAll(".boardMain-main-board-img-box"); // select post container
var imageElements2 = document.querySelectorAll(".imageContainer img"); // Select the image elements of the post
// Hide previous button initially if it's the first image
var previousButton2 = document.querySelector(".boardMain-board-paging-left");
// Show next button if there are more images
var nextButtonBoard2 = document.querySelector(".boardMain-board-paging-right");
// Hide all buttons if there is only one image
if (imageElements2.length === 1) {
    nextButtonBoard2.style.display = "none";
    previousButton2.style.display = "none";
}

const board_img_paging = (imgPage) => {
    for (let p = 0; p < fileSize; p++) {
        // 부모 요소 선택
        var parentElement = document.querySelector('.boardMain-board-paging-num');

        // 자식 요소 생성
        var childElement = document.createElement('div');
        childElement.className = 'boardMain-board-paging-num-on';
        var childElementOff = document.createElement('div');
        childElementOff.className = 'boardMain-board-paging-num-off';

        // 자식 요소를 부모 요소의 하위로 추가
        if (p == imgPage) {
            parentElement.appendChild(childElementOff);
        } else {
            parentElement.appendChild(childElement);
        }
    }
}

const memberSearchInput = document.getElementById('nav-search-b-m-s-input');
const memberSearchOaF = () => {
    const searchBox = $('#navSearchMenu');
    const navMenuBox = $('#navMenuBox');
    const navLogo = document.getElementById('searchImg');
    const navLogo2 = document.getElementById('searchImg2');
    if (searchBox.hasClass("nav-search-b2")) {
        searchBox.removeClass("nav-search-b2");
        if (navMenuBox.width() === 311) {
            navMenuBox.removeClass("boardMain-nav-margin");
            navMenuBox.addClass("boardMain-nav-margin1"); // 311
            searchBox.addClass("nav-search-b");
            setTimeout(function () {
                navLogo.style.display = "none";
                navLogo2.style.display = "block";
            }, 500);
        } else if (navMenuBox.width() === 220) {
            navMenuBox.removeClass("boardMain-nav-margin");
            navMenuBox.addClass("boardMain-nav-margin2"); //220
            searchBox.addClass("nav-search-b");
            setTimeout(function () {
                navLogo.style.display = "none";
                navLogo2.style.display = "block";
            }, 500);
        } else {
            searchBox.addClass("nav-search-b");
        }
    } else if (searchBox.hasClass("nav-search-b1")) {
        searchBox.removeClass("nav-search-b1");
        if (navMenuBox.hasClass("boardMain-nav-margin4")) {
            navMenuBox.removeClass("boardMain-nav-margin4");
            navMenuBox.addClass("boardMain-nav-margin1");
            searchBox.addClass("nav-search-b");
            setTimeout(function () {
                navLogo.style.display = "none";
                navLogo2.style.display = "block";
            }, 500);
        } else if (navMenuBox.hasClass("boardMain-nav-margin5")) {
            navMenuBox.removeClass("boardMain-nav-margin5");
            navMenuBox.addClass("boardMain-nav-margin2");
            searchBox.addClass("nav-search-b");
            setTimeout(function () {
                navLogo.style.display = "none";
                navLogo2.style.display = "block";
            }, 500);
        } else {
            searchBox.addClass("nav-search-b");
        }
    } else {
        searchBox.removeClass("nav-search-b");
        if (navMenuBox.hasClass("boardMain-nav-margin1")) {
            navMenuBox.removeClass("boardMain-nav-margin1");
            navMenuBox.addClass("boardMain-nav-margin4"); // 길어지기 311
            searchBox.addClass("nav-search-b1");
            setTimeout(function () {
                navLogo.style.display = "block";
                navLogo2.style.display = "none";
            }, 500);
        } else if (navMenuBox.hasClass("boardMain-nav-margin2")) {
            navMenuBox.removeClass("boardMain-nav-margin2");
            navMenuBox.addClass("boardMain-nav-margin5"); // 길어지기 220
            searchBox.addClass("nav-search-b1");
            setTimeout(function () {
                navLogo.style.display = "block";
                navLogo2.style.display = "none";
            }, 500);
        } else {
            searchBox.addClass("nav-search-b1");
        }
        setTimeout(function () {
            navMenuBox.removeClass("boardMain-nav-margin5");
            navMenuBox.removeClass("boardMain-nav-margin4");
            navMenuBox.addClass("boardMain-nav-margin"); // 길어지기 220
            searchBox.removeClass("nav-search-b1");
            searchBox.addClass("nav-search-b2");
            navLogo.style.display = "";
            navLogo2.style.display = "";
            memberSearchInput.value = "";
        }, 1000);
    }
}
let debounceTimeout;

// 사용자 입력을 처리하는 debounce 함수
function debounce(func, delay) {
    clearTimeout(debounceTimeout);
    debounceTimeout = setTimeout(func, delay);
}

memberSearchInput.addEventListener('keyup', function () {
    const searchMemberResult = document.getElementById('searchMemberResult');
        debounce(function () {
            let keyResult = memberSearchInput.value;
            axios({
                method: "post",
                url: "/member/search",
                data: {
                    q: keyResult
                }
            }).then(res => {
                if (keyResult === "") {
                    let iinput = "";
                    iinput += '<div class="nav-search-b-m-s-l-i">\n' +
                        '                                                                        <span class="nav-search-b-m-s-l-i-">최근 검색 항목</span>\n' +
                        '                                                                        <div class="nav-search-b-m-s-l-i-1">모두 지우기</div>\n' +
                        '                                                                    </div>\n' +
                        '                                                                    <ul class="_abo1">\n' +
                        '                                                                        <div>\n' +
                        '                                                                            <a href="#" class="search-m">\n' +
                        '                                                                                <div class="search-m-">\n' +
                        '                                                                                    <div class="search-m-1">\n' +
                        '                                                                                        <div class="search-m-2">\n' +
                        '                                                                                            <div class="search-m-i">\n' +
                        '                                                                                                <div class="search-m-i-">\n' +
                        '                                                                                                    <div class="search-m-i-1">\n' +
                        '                                                                                                        <canvas class="search-m-i-canvas" height="54" width="54"></canvas>\n' +
                        '                                                                                                        <span class="search-m-i-2">\n' +
                        '\n' +
                        '                                                                                                            <!--검색 기록 이미지-->\n' +
                        '                                                                                                            <img src="../images/test프로필.jpg" class="search-m-i-img">\n' +
                        '                                                                                                        </span>\n' +
                        '                                                                                                    </div>\n' +
                        '                                                                                                </div>\n' +
                        '                                                                                            </div>\n' +
                        '                                                                                            <div class="search-m-it">\n' +
                        '                                                                                                <div class="search-m-it-">\n' +
                        '                                                                                                    <div class="search-m-it-1">\n' +
                        '                                                                                                        <div class="search-m-it-2">\n' +
                        '\n' +
                        '                                                                                                            <!--검색 기록 사용자 닉네임-->\n' +
                        '                                                                                                            <span class="search-m-it-3">karina_aespas_</span>\n' +
                        '                                                                                                        </div>\n' +
                        '                                                                                                        <span class="search-m-it-4">\n' +
                        '                                                                                                            <span class="search-m-it-5">AESPA KARINA 카리나 • 팔로잉</span>\n' +
                        '                                                                                                        </span>\n' +
                        '                                                                                                    </div>\n' +
                        '                                                                                                </div>\n' +
                        '                                                                                            </div>\n' +
                        '                                                                                            <div class="search-m-ite">\n' +
                        '                                                                                                <div class="search-m-ite-">\n' +
                        '                                                                                                    <div class="search-m-ite-1">\n' +
                        '                                                                                                        <div class="search-m-ite-2">\n' +
                        '                                                                                                            <svg aria-label="닫기" class="close-icon" color="rgb(115, 115, 115)" fill="rgb(115, 115, 115)" height="16" role="img" viewBox="0 0 24 24" width="16">\n' +
                        '                                                                                                                <title>닫기</title>\n' +
                        '                                                                                                                <polyline fill="none" points="20.643 3.357 12 12 3.353 20.647" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="3"></polyline>\n' +
                        '                                                                                                                <line fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="3" x1="20.649" x2="3.354" y1="20.649" y2="3.354"></line>\n' +
                        '                                                                                                            </svg>\n' +
                        '                                                                                                        </div>\n' +
                        '                                                                                                    </div>\n' +
                        '                                                                                                </div>\n' +
                        '                                                                                            </div>\n' +
                        '                                                                                        </div>\n' +
                        '                                                                                    </div>\n' +
                        '                                                                                </div>\n' +
                        '                                                                            </a>\n' +
                        '                                                                        </div>\n' +
                        '                                                                    </ul>'
                    searchMemberResult.innerHTML = iinput;
                } else {
                    let output = "";
                    if (res.data.length === 0) {
                        output += '<div class="nav-search-b-m-u-x">검색 결과가 없습니다.</div>';
                    } else {
                        for (let i = 0; i < res.data.length; i++) {
                            output += '<div class="_abn_">\n' +
                                '                                            <a href="/member/myPage1/' + res.data[i].id + '" class="search-m-u">\n' +
                                '                                                <div class="search-m-u-">\n' +
                                '                                                    <div class="search-m-u-1">\n' +
                                '                                                        <div class="search-m-u-2">\n' +
                                '                                                            <div class="search-m-u-i">\n' +
                                '                                                                <div class="search-m-u-i-">\n' +
                                '                                                                    <div class="search-m-u-i-1">\n' +
                                '                                                                        <canvas class="search-m-i-canvas" height="54" width="54"></canvas>\n' +
                                '                                                                        <span class="search-m-u-i-2">\n' +
                                '                                                                    <img src="/upload/' + res.data[i].memberProfile + '" class="search-m-u-i-img">\n' +
                                '                                                                </span>\n' +
                                '                                                                    </div>\n' +
                                '                                                                </div>\n' +
                                '                                                            </div>\n' +
                                '                                                            <div class="search-m-u-it">\n' +
                                '                                                                <div class="search-m-u-it-">\n' +
                                '                                                                    <div class="search-m-u-it-1">\n' +
                                '                                                                        <div class="search-m-u-it-2">\n' +
                                '                                                                            <span class="search-m-u-it-3">' + res.data[i].memberNickName + '</span>\n' +
                                '                                                                        </div>\n' +
                                '                                                                        <span class="search-m-u-it-4">\n' +
                                '                                                                    <span class="search-m-u-it-5">' + res.data[i].memberName + '</span>\n' +
                                '                                                                </span>\n' +
                                '                                                                    </div>\n' +
                                '                                                                </div>\n' +
                                '                                                            </div>\n' +
                                '                                                        </div>\n' +
                                '                                                    </div>\n' +
                                '                                                </div>\n' +
                                '                                            </a>\n' +
                                '                                        </div>'
                        }
                    }
                    searchMemberResult.innerHTML = output;
                }
            }).catch(err => {
                console.log("실패");
            });
        }, 1000); // 1초 지연
});

const inputClear = () => {
    memberSearchInput.value = "";
}