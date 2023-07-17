//    게시글 디테일 연결
let i = 0;
let fileSize = 1;
const mainBoardDetailOff = (boardId) => {
    const mainDetail = document.getElementById('mainBoardDetail' + boardId);
    console.log("boardId="+ boardId);
    i = 0;
    mainDetail.style.display = "none";

}
const fuckyou = () => {
    const fuckyouyou = document.getElementById('plus-modal-board-');
    fuckyouyou.style.display = "none";
}

const openMainDetail = (id) => {
    console.log("id =" + id);
    const boardKind = 1;
    const mainDetail = document.getElementById('mainBoardDetail' + id);
    axios({
        method: "get",
        url: "/boardDetail/" + id + "?boardKind=" + boardKind
    }).then(res => {
        const board = res.data;
        const boardDTO = board.boardDTO;
        const memberDTO = board.memberDTO;
        const boardFileList = board.boardFileList;
        const storedFileName = boardFileList[i].storedFileName;
        const boardLike = board.boardLike;  //boolean
        const boardBookmark = board.boardBookmark;  //boolean
        fileSize = boardFileList.length;

        let output = "<div class=\"myPage-board-detail-b1\">\n" +
            "                        <div class=\"myPage-board-detail-b2\">\n" +
            "                            <div class=\"myPage-board-detail-b3\">\n" +
            "                                <div class=\"myPage-board-detail-background\"></div>\n" +
            "\n" +
            "                                <div class=\"myPage-board-detail-on-off-box\">\n" +
            "                                    <div class=\"myPage-board-detail-on-off-size\">\n" +
            "                                        <div class=\"myPage-board-detail-size1\">\n" +
            "                                            <!--게시글 디테일 on/off버튼 위치-->\n" +
            "                                            <svg aria-label=\"닫기\" class=\"myPage-board-detail-btn\" id=\"myPage-board-detail-btn\" \n" +
            "                                                 color=\"rgb(255, 255, 255)\" fill=\"rgb(255, 255, 255)\" height=\"18\"\n" +
            "                                                 role=\"img\" viewBox=\"0 0 24 24\" width=\"18\" onclick=\"mainBoardDetailOff(" + boardDTO.id + ")\">\n" +
            "                                                <title>닫기</title>\n" +
            "                                                <polyline fill=\"none\" points=\"20.643 3.357 12 12 3.353 20.647\"\n" +
            "                                                          stroke=\"currentColor\" stroke-linecap=\"round\"\n" +
            "                                                          stroke-linejoin=\"round\" stroke-width=\"3\"></polyline>\n" +
            "                                                <line fill=\"none\" stroke=\"currentColor\" stroke-linecap=\"round\"\n" +
            "                                                      stroke-linejoin=\"round\" stroke-width=\"3\" x1=\"20.649\" x2=\"3.354\"\n" +
            "                                                      y1=\"20.649\" y2=\"3.354\"></line>\n" +
            "                                            </svg>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "\n" +
            "                                <div class=\"myPage-board-detail-inherit\">\n" +
            "                                    <div class=\"myPage-board-detail-dsp1\">\n" +
            "                                        <div class=\"myPage-board-detail-dsp2\">\n" +
            "                                            <div class=\"myPage-board-detail-dsp3\">\n" +
            "                                                <div class=\"myPage-board-detail-dsp4\">\n" +
            "\n" +
            "                                                    <!--다음 게시물 next버튼-->\n" +
            "                                                    <div>\n" +
            "                                                        <div class=\"myPage-board-detail-box-next\">\n" +
            "                                                            <div class=\"myPage-board-detail-box-next-size\">\n" +
            "                                                                <!--이전 게시물 버튼-->\n" +
            "                                                                <div class=\"detail-box-back\">\n" +
            "                                                                    <button class=\"detail-box-back-btn\" type=\"button\">\n" +
            "                                                                        <div class=\"detail-box-back-btn-lz\">\n" +
            "                                                                            <span style=\"display: inline-block;transform: rotate(270deg);\">\n" +
            "                                                                                <svg aria-label=\"돌아가기\"\n" +
            "                                                                                     class=\"detail-box-back-btn-logo\"\n" +
            "                                                                                     color=\"rgb(0, 0, 0)\"\n" +
            "                                                                                     fill=\"rgb(0, 0, 0)\" height=\"16\"\n" +
            "                                                                                     role=\"img\" viewBox=\"0 0 24 24\"\n" +
            "                                                                                     width=\"16\">\n" +
            "                                                                                    <title>돌아가기</title>\n" +
            "                                                                                    <path d=\"M21 17.502a.997.997 0 0 1-.707-.293L12 8.913l-8.293 8.296a1 1 0 1 1-1.414-1.414l9-9.004a1.03 1.03 0 0 1 1.414 0l9 9.004A1 1 0 0 1 21 17.502Z\"></path>\n" +
            "                                                                                </svg>\n" +
            "                                                                            </span>\n" +
            "                                                                        </div>\n" +
            "                                                                    </button>\n" +
            "                                                                </div>\n" +
            "                                                                <!--다음 게시물 버튼-->\n" +
            "                                                                <div class=\"detail-box-next\">\n" +
            "                                                                    <button class=\"detail-box-next-btn\" type=\"button\">\n" +
            "                                                                        <div class=\"detail-box-next-btn-rz\">\n" +
            "                                                                            <span style=\"display: inline-block;transform: rotate(90deg);\">\n" +
            "                                                                                <svg aria-label=\"다음\"\n" +
            "                                                                                     class=\"detail-box-next-btn-logo\"\n" +
            "                                                                                     color=\"rgb(0, 0, 0)\"\n" +
            "                                                                                     fill=\"rgb(0, 0, 0)\" height=\"16\"\n" +
            "                                                                                     role=\"img\" viewBox=\"0 0 24 24\"\n" +
            "                                                                                     width=\"16\">\n" +
            "                                                                                    <title>다음</title>\n" +
            "                                                                                    <path d=\"M21 17.502a.997.997 0 0 1-.707-.293L12 8.913l-8.293 8.296a1 1 0 1 1-1.414-1.414l9-9.004a1.03 1.03 0 0 1 1.414 0l9 9.004A1 1 0 0 1 21 17.502Z\"></path>\n" +
            "                                                                                </svg>\n" +
            "                                                                            </span>\n" +
            "                                                                        </div>\n" +
            "                                                                    </button>\n" +
            "                                                                </div>\n" +
            "                                                            </div>\n" +
            "\n" +
            "                                                        </div>\n" +
            "                                                    </div>\n" +
            "\n" +
            "                                                    <!--게시글 디테일 본문-->\n" +
            "                                                    <div class=\"detail-box-body-1\">\n" +
            "                                                        <div class=\"detail-box-body-2\">\n" +
            "                                                            <article class=\"detail-box-body-inner\" tabindex=\"-1\">\n" +
            "                                                                <div class=\"detail-inherit\">\n" +
            "                                                                    <div class=\"detail-basis\">\n" +
            "                                                                        <div class=\"dlrjsanjfRK\">\n" +
            "                                                                            <div style=\"position: relative\">\n" +
            "                                                                                <div class=\"wlsWKahfmrpTekdlrjs\"></div>\n" +
            "                                                                                <div class=\"detail-img-box\">\n" +
            "                                                                                    <div class=\"detail-img-box-inner\">\n" +
            "                                                                                        <div class=\"detail-img-box-size\">\n" +
            "                                                                                            <div class=\"detail-img-box-div\">\n" +
            "\n" +
            "                                                                                                <!--게시판 상세 이미지 넣을 곳-->\n" +
            "                                                                                                <ul class=\"detail-img-ul\">\n" +
            "                                                                                                    <li class=\"detail-img-li\">\n" +
            "                                                                                                        <div class=\"detail-img-li-inner\">\n" +
            "                                                                                                            <div class=\"detail-img-dlrjsanjfRK\">\n" +
            "                                                                                                                <div>\n" +
            "                                                                                                                    <div class=\"detail-img-sc\">\n" +
            "                                                                                                                        <div class=\"detail-img-sc-inner\">\n" +
            "                                                                                                                            <img class=\"detail-re-img-size\"\n" +
            "                                                                                                                                 src=\"/upload/" + storedFileName + "\"" +
            "                                                                                                                                 >\n" +
            "                                                                                                                        </div>\n" +
            "                                                                                                                        <div style=\"position: absolute;top: 0;left: 0;right: 0;bottom: 0;\"></div>\n" +
            "                                                                                                                    </div>\n" +
            "                                                                                                                </div>\n" +
            "                                                                                                            </div>\n" +
            "                                                                                                        </div>\n" +
            "                                                                                                    </li>\n" +
            "                                                                                                </ul>\n" +
            "\n" +
            "                                                                                            </div>\n" +
            "                                                                                        </div>\n" +
            "\n" +
            "                                                                                        <!--페이지 이동 버튼-->\n" +
            "                                                                                        <button aria-label=\"돌아가기\"\n" +
            "                                                                                                class=\"detail-img-back-btn\" id=\"detail-back-btn\" style=\"display: none;\" onclick=\"detail_file_back(" + boardDTO.id + ")\">\n" +
            "                                                                                            <div class=\"detail-img-back-btn-logo\"></div>\n" +
            "                                                                                        </button>\n" +
            "                                                                                        <button aria-label=\"다음\"\n" +
            "                                                                                                class=\"detail-img-next-btn\" id=\"detail-next-btn\" onclick=\"detail_file_next(" + boardDTO.id + ")\">\n" +
            "                                                                                            <div class=\"detail-img-next-btn-logo\"></div>\n" +
            "                                                                                        </button>\n" +
            "\n" +
            "                                                                                    </div>\n" +
            "                                                                                </div>\n" +
            "                                                                            </div>\n" +
            "\n" +
            "                                                                            <!--하단의 페이징 넘버-->\n" +
            "                                                                            <div class=\"detail-img-bottom-num-box\">\n" +
            // "                                                                                <div class=\"detail-img-bottom-num-on\"></div>\n" +
            // "                                                                                <div class=\"detail-img-bottom-num-off\"></div>\n" +
            // "                                                                                <div class=\"detail-img-bottom-num-on\"></div>\n" +
            "                                                                            </div>\n" +
            "\n" +
            "                                                                        </div>\n" +
            "                                                                    </div>\n" +
            "\n" +
            "                                                                    <div class=\"detail-board-contents-box\">\n" +
            "                                                                        <div class=\"detail-board-contents-box-inner\">\n" +
            "                                                                            <div class=\"detail-board-contents-box-lo\">\n" +
            "                                                                                <div class=\"detail-board-contents-user-info\">\n" +
            "                                                                                    <div class=\"detail-board-user-info-size\">\n" +
            "                                                                                        <header class=\"detail-contents-header\">\n" +
            "                                                                                            <div>\n" +
            "                                                                                                <div>\n" +
            "                                                                                                    <div>\n" +
            "                                                                                                        <div style=\"position: relative;\">\n" +
            "                                                                                                            <canvas class=\"_aarh\"\n" +
            "                                                                                                                    height=\"42\"\n" +
            "                                                                                                                    width=\"42\"></canvas>\n" +
            "\n" +
            "                                                                                                            <!--게시글 상세페이지 유저 사진-->\n" +
            "                                                                                                            <a href=\"#\"\n" +
            "                                                                                                               class=\"user-myPage-link\">\n" +
            "                                                                                                                <img \n" +
            "                                                                                                                     class=\"user-myPage-link-img\"\n" +
            "                                                                                                                     src=\"/upload/" + memberDTO.memberProfile + "\"" +
            "                                                                                                                     >\n" +
            "                                                                                                            </a>\n" +
            "\n" +
            "                                                                                                        </div>\n" +
            "                                                                                                    </div>\n" +
            "                                                                                                </div>\n" +
            "                                                                                            </div>\n" +
            "\n" +
            "                                                                                            <!--게시글 상세페이지 contents 공간-->\n" +
            "                                                                                            <div class=\"detail-board-contents-user-nick\">\n" +
            "                                                                                                <div style=\"display: flex;max-width: 100%;\">\n" +
            "                                                                                                    <div style=\"display: flex;\">\n" +
            "                                                                                                        <div class=\"detail-board-user-nick-padding\">\n" +
            "                                                                                                            <div class=\"detail-board-user-nick-box-size\">\n" +
            "                                                                                                                <span class=\"wjdakftlfgspdy\">\n" +
            "                                                                                                                    <div style=\"display: inline;\">\n" +
            "                                                                                                                        <div style=\"display: inline;\">\n" +
            "                                                                                                                            <!--게시글 상세페이지 유저 닉네임-->\n" +
            "                                                                                                                            <a href=\"#\"\n" +
            "                                                                                                                               class=\"detail-board-user-nick-link\">" + memberDTO.memberNickName + "</a>\n" +
            "                                                                                                                        </div>\n" +
            "                                                                                                                    </div>\n" +
            "                                                                                                                </span>\n" +
            "                                                                                                            </div>\n" +
            "                                                                                                        </div>\n" +
            "                                                                                                    </div>\n" +
            "                                                                                                </div>\n" +
            "                                                                                            </div>\n" +
            "                                                                                        </header>\n" +
            "                                                                                        <div style=\"padding-right: 8px;\">\n" +
            "                                                                                            <div class=\"detail-board-plus-box\">\n" +
            "                                                                                                <div class=\"detail-board-plus-box-inner\">\n" +
            "                                                                                                    <div class=\"ejqhrldksWhrdlqslek\">\n" +
            "                                                                                                        <svg aria-label=\"옵션 더 보기\"\n" +
            "                                                                                                             class=\"_ab6-\"\n" +
            "                                                                                                             color=\"rgb(0, 0, 0)\"\n" +
            "                                                                                                             fill=\"rgb(0, 0, 0)\"\n" +
            "                                                                                                             height=\"24\"\n" +
            "                                                                                                             role=\"img\"\n" +
            "                                                                                                             viewBox=\"0 0 24 24\"\n" +
            "                                                                                                             width=\"24\">\n" +
            "                                                                                                            <circle cx=\"12\"\n" +
            "                                                                                                                    cy=\"12\"\n" +
            "                                                                                                                    r=\"1.5\"></circle>\n" +
            "                                                                                                            <circle cx=\"6\"\n" +
            "                                                                                                                    cy=\"12\"\n" +
            "                                                                                                                    r=\"1.5\"></circle>\n" +
            "                                                                                                            <circle cx=\"18\"\n" +
            "                                                                                                                    cy=\"12\"\n" +
            "                                                                                                                    r=\"1.5\"></circle>\n" +
            "                                                                                                        </svg>\n" +
            "                                                                                                    </div>\n" +
            "                                                                                                </div>\n" +
            "                                                                                            </div>\n" +
            "                                                                                        </div>\n" +
            "                                                                                    </div>\n" +
            "                                                                                </div>\n" +
            "                                                                                <div class=\"detail-board-contents-comment\">\n" +
            "                                                                                    <section class=\"detail-board-like-\">\n" +
            "                                                                                        <span style=\"display: inline-block;margin-left: -8px\">\n" +
            "                                                                                            <div class=\"detail-board-like-1\">\n" +
            "                                                                                                <div class=\"detail-board-like-2\">\n" +
            "                                                                                                    <span>\n" +
            "                                                                                                        <svg aria-label=\"좋아요\"\n" +
            "                                                                                                             class=\"detail-board-like\"\n" +
            "                                                                                                             id=\"board-like\"\n" +
            "                                                                                                             onclick=\"board_like(" + boardDTO.id + ")\"\n" +
            "                                                                                                             color=\"rgb(38, 38, 38)\"\n" +
            "                                                                                                             fill=\"rgb(38, 38, 38)\"\n" +
            "                                                                                                             height=\"24\"\n" +
            "                                                                                                             role=\"img\"\n" +
            "                                                                                                             viewBox=\"0 0 24 24\"\n" +
            "                                                                                                             width=\"24\">\n" +
            "                                                                                                            <title>좋아요</title>\n" +
            "                                                                                                            <path d=\"M16.792 3.904A4.989 4.989 0 0 1 21.5 9.122c0 3.072-2.652 4.959-5.197 7.222-2.512 2.243-3.865 3.469-4.303 3.752-.477-.309-2.143-1.823-4.303-3.752C5.141 14.072 2.5 12.167 2.5 9.122a4.989 4.989 0 0 1 4.708-5.218 4.21 4.21 0 0 1 3.675 1.941c.84 1.175.98 1.763 1.12 1.763s.278-.588 1.11-1.766a4.17 4.17 0 0 1 3.679-1.938m0-2a6.04 6.04 0 0 0-4.797 2.127 6.052 6.052 0 0 0-4.787-2.127A6.985 6.985 0 0 0 .5 9.122c0 3.61 2.55 5.827 5.015 7.97.283.246.569.494.853.747l1.027.918a44.998 44.998 0 0 0 3.518 3.018 2 2 0 0 0 2.174 0 45.263 45.263 0 0 0 3.626-3.115l.922-.824c.293-.26.59-.519.885-.774 2.334-2.025 4.98-4.32 4.98-7.94a6.985 6.985 0 0 0-6.708-7.218Z\"></path>\n" +
            "                                                                                                        </svg>\n" +
            "                                                                                                        <svg aria-label=\"좋아요 취소\"\n" +
            "                                                                                                             class=\"detail-board-like\"\n" +
            "                                                                                                             id=\"board-unLike\"\n" +
            "                                                                                                             onclick=\"board_unLike(" + boardDTO.id + ")\"\n" +
            "                                                                                                             color=\"rgb(255, 48, 64)\"\n" +
            "                                                                                                             fill=\"rgb(255, 48, 64)\"\n" +
            "                                                                                                             height=\"24\"\n" +
            "                                                                                                             role=\"img\"\n" +
            "                                                                                                             viewBox=\"0 0 48 48\"\n" +
            "                                                                                                             width=\"24\">\n" +
            "                                                                                                            <title>좋아요 취소</title>\n" +
            "                                                                                                            <path d=\"M34.6 3.1c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5s1.1-.2 1.6-.5c1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z\"></path>\n" +
            "                                                                                                        </svg>\n" +
            "                                                                                                    </span>\n" +
            "                                                                                                </div>\n" +
            "                                                                                            </div>\n" +
            "                                                                                        </span>\n" +
            "                                                                                        <span style=\"display: inline-block;\">\n" +
            "                                                                                            <div class=\"detail-board-like-1\">\n" +
            "                                                                                                <div class=\"detail-board-like-2\">\n" +
            "                                                                                                    <span>\n" +
            "                                                                                                        <svg aria-label=\"댓글 달기\"\n" +
            "                                                                                                             class=\"detail-board-comment-focs\"\n" +
            "                                                                                                             color=\"rgb(0, 0, 0)\"\n" +
            "                                                                                                             fill=\"rgb(0, 0, 0)\"\n" +
            "                                                                                                             height=\"24\"\n" +
            "                                                                                                             role=\"img\"\n" +
            "                                                                                                             viewBox=\"0 0 24 24\"\n" +
            "                                                                                                             width=\"24\">\n" +
            "                                                                                                            <title>댓글 달기</title>\n" +
            "                                                                                                            <path d=\"M20.656 17.008a9.993 9.993 0 1 0-3.59 3.615L22 22Z\"\n" +
            "                                                                                                                  fill=\"none\"\n" +
            "                                                                                                                  stroke=\"currentColor\"\n" +
            "                                                                                                                  stroke-linejoin=\"round\"\n" +
            "                                                                                                                  stroke-width=\"2\"></path>\n" +
            "                                                                                                        </svg>\n" +
            "                                                                                                    </span>\n" +
            "                                                                                                </div>\n" +
            "                                                                                            </div>\n" +
            "                                                                                        </span>\n" +
            "                                                                                        <span class=\"detail-board-book-mark-\">\n" +
            "                                                                                            <div class=\"detail-board-like-1\">\n" +
            "                                                                                                <div class=\"detail-board-like-2\">\n" +
            "                                                                                                    <span>\n" +
            "                                                                                                        <svg aria-label=\"저장\"\n" +
            "                                                                                                             class=\"detail-board-book-mark\"\n" +
            "                                                                                                             id=\"board-bookmark\"\n" +
            "                                                                                                             onclick=\"board_bookmark(" + boardDTO.id + ")\"\n" +
            "                                                                                                             color=\"rgb(0, 0, 0)\"\n" +
            "                                                                                                             fill=\"rgb(0, 0, 0)\"\n" +
            "                                                                                                             height=\"24\"\n" +
            "                                                                                                             role=\"img\"\n" +
            "                                                                                                             viewBox=\"0 0 24 24\"\n" +
            "                                                                                                             width=\"24\">\n" +
            "                                                                                                            <title>저장</title>\n" +
            "                                                                                                            <polygon\n" +
            "                                                                                                                    fill=\"none\"\n" +
            "                                                                                                                    points=\"20 21 12 13.44 4 21 4 3 20 3 20 21\"\n" +
            "                                                                                                                    stroke=\"currentColor\"\n" +
            "                                                                                                                    stroke-linecap=\"round\"\n" +
            "                                                                                                                    stroke-linejoin=\"round\"\n" +
            "                                                                                                                    stroke-width=\"2\"></polygon>\n" +
            "                                                                                                        </svg>\n" +
            "                                                                                                        <svg aria-label=\"삭제\"\n" +
            "                                                                                                             class=\"detail-board-book-mark\"\n" +
            "                                                                                                             id=\"board-unBookmark\"\n" +
            "                                                                                                             onclick=\"board_unBookmark(" + boardDTO.id + ")\"\n" +
            "                                                                                                             color=\"rgb(0, 0, 0)\"\n" +
            "                                                                                                             fill=\"rgb(0, 0, 0)\"\n" +
            "                                                                                                             height=\"24\"\n" +
            "                                                                                                             role=\"img\"\n" +
            "                                                                                                             viewBox=\"0 0 24 24\"\n" +
            "                                                                                                             width=\"24\">\n" +
            "                                                                                                            <title>삭제</title>\n" +
            "                                                                                                            <path d=\"M20 22a.999.999 0 0 1-.687-.273L12 14.815l-7.313 6.912A1 1 0 0 1 3 21V3a1 1 0 0 1 1-1h16a1 1 0 0 1 1 1v18a1 1 0 0 1-1 1Z\"></path>\n" +
            "                                                                                                        </svg>\n" +
            "                                                                                                    </span>\n" +
            "                                                                                                </div>\n" +
            "                                                                                            </div>\n" +
            "                                                                                        </span>\n" +
            "                                                                                    </section>\n" +
            "\n" +
            "                                                                                    <!--게시글 상세 코멘트 리스트 시작-->\n" +
            "                                                                                    <div class=\"detail-board-contents-comment-list\">\n" +
            "                                                                                        <ul class=\"detail-board-contents-comment-list-\">\n" +
            "                                                                                            <ul class=\"detail-board-contents-comment-list-1\">\n" +
            "                                                                                                <div class=\"detail-board-contents-comment-box\">\n" +
            "                                                                                                    <li class=\"detail-board-comment-list-li\">\n" +
            "                                                                                                        <div class=\"detail-board-comment-size\">\n" +
            "                                                                                                            <div class=\"_a9zo\">\n" +
            "                                                                                                                <div>\n" +
            "                                                                                                                    <div>\n" +
            "                                                                                                                        <div class=\"eotrmftkdydwkdlalwl\">\n" +
            "                                                                                                                            <canvas class=\"_aarh\"\n" +
            "                                                                                                                                    height=\"42\"\n" +
            "                                                                                                                                    width=\"42\"></canvas>\n" +
            "\n" +
            "                                                                                                                            <!--게시글 상세 댓글을 단 유저의 이미지-->\n" +
            "                                                                                                                            <a href=\"#\"\n" +
            "                                                                                                                               class=\"user-myPage-link\">\n" +
            "                                                                                                                                <img \n" +
            "                                                                                                                                     class=\"user-myPage-link-img\">\n" +
            "                                                                                                                            </a>\n" +
            "\n" +
            "                                                                                                                        </div>\n" +
            "                                                                                                                    </div>\n" +
            "                                                                                                                </div>\n" +
            "\n" +
            "                                                                                                                <div class=\"_a9zr\">\n" +
            "                                                                                                                    <h3 class=\"_a9zc\">\n" +
            "                                                                                                                        <div class=\"dhfmsWhrakwls\">\n" +
            "                                                                                                                            <div style=\"display: inline;\">\n" +
            "                                                                                                                                <div style=\"display: inline;\">\n" +
            "\n" +
            "                                                                                                                                    <!--게시글 상세 댓글을 단 사용자 myPage 이동 링크-->\n" +
            "                                                                                                                                    <a href=\"#\"\n" +
            "                                                                                                                                       class=\"comment-user-going\"> 댓글 단 사람 닉네임(변경필)" + memberDTO.memberNickName + "</a>\n" +
            "\n" +
            "                                                                                                                                </div>\n" +
            "                                                                                                                            </div>\n" +
            "                                                                                                                        </div>\n" +
            "                                                                                                                    </h3>\n" +
            "\n" +
            "                                                                                                                    <!--게시글 상세 댓글 내용-->\n" +
            "                                                                                                                    <div style=\"display: inline\">\n" +
            "                                                                                                                        <span class=\"detail-board-comment-text\">1</span>\n" +
            "                                                                                                                    </div>\n" +
            "\n" +
            "                                                                                                                    <div class=\"detail-board-comment-info\">\n" +
            "                                                                                                                        <span class=\"detail-board-comment-info-line\">\n" +
            "\n" +
            "                                                                                                                            <!--게시글 상세 댓글 작성 시간-->\n" +
            "                                                                                                                            <a class=\"detail-board-comment-time-box\">\n" +
            "                                                                                                                                <time class=\"detail-board-comment-time\"\n" +
            "                                                                                                                                      datetime=\"2023-07-02T14:50:05.000Z\"\n" +
            "                                                                                                                                      title=\"7월 2, 2023\">3시간</time>\n" +
            "                                                                                                                            </a>\n" +
            "\n" +
            "                                                                                                                            <!--게시글 상세 댓글에 답글 디스플레이 on / off-->\n" +
            "                                                                                                                            <button type=\"button\"\n" +
            "                                                                                                                                    class=\"_a9ze\">\n" +
            "                                                                                                                                <span class=\"aksemfRKrhalswnd\">답글 달기</span>\n" +
            "                                                                                                                            </button>\n" +
            "\n" +
            "                                                                                                                        </span>\n" +
            "                                                                                                                    </div>\n" +
            "                                                                                                                </div>\n" +
            "\n" +
            "                                                                                                            </div>\n" +
            "                                                                                                        </div>\n" +
            "                                                                                                    </li>\n" +
            "                                                                                                </div>\n" +
            "                                                                                            </ul>\n" +
            "                                                                                        </ul>\n" +
            "                                                                                    </div>\n" +
            "\n" +
            "                                                                                    <section\n" +
            "                                                                                            class=\"detail-board-comment-save\">\n" +
            "                                                                                        <div>\n" +
            "\n" +
            "                                                                                            <!--게시글 상세 댓글 작성 공간-->\n" +
            "                                                                                            <form class=\"detail-board-comment-save-form\">\n" +
            "                                                                                                <div class=\"detail-board-comment-save-box\">\n" +
            "                                                                                                    <div class=\"comment-textarea-padding\">\n" +
            "                                                                                                        &nbsp;\n" +
            "                                                                                                    </div>\n" +
            "                                                                                                    <textarea\n" +
            "                                                                                                            aria-label=\"댓글 달기\"\n" +
            "                                                                                                            placeholder=\"댓글 달기...\"\n" +
            "                                                                                                            class=\"detail-board-comment-save-textarea\"></textarea>\n" +
            "                                                                                                    <div style=\"margin-left: 8px;\">\n" +
            "                                                                                                        <div class=\"detail-comment-sub-btn-box\">\n" +
            "                                                                                                            <span style=\"opacity: .5;\">게시</span>\n" +
            "                                                                                                        </div>\n" +
            "                                                                                                    </div>\n" +
            "                                                                                                </div>\n" +
            "                                                                                            </form>\n" +
            "\n" +
            "                                                                                        </div>\n" +
            "                                                                                    </section>\n" +
            "                                                                                </div>\n" +
            "                                                                            </div>\n" +
            "                                                                        </div>\n" +
            "                                                                    </div>\n" +
            "\n" +
            "                                                                </div>\n" +
            "                                                            </article>\n" +
            "                                                        </div>\n" +
            "                                                    </div>\n" +
            "\n" +
            "                                                </div>\n" +
            "                                            </div>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>"
        mainDetail.style.display = "";
        mainDetail.innerHTML = output;

        // 사진 next, back 버튼 제어
        if (fileSize == 1) {
            nextBtn_controller("off");
            backBtn_controller("off");
        } else if (fileSize == (i + 1)) {
            nextBtn_controller("off");
            backBtn_controller("on");
        } else if (fileSize > (i + 1) && i != 0) {
            backBtn_controller("on");
        } else if (i == 0) {
            backBtn_controller("off");
        }

        // 좋아요 버튼 제어
        if (boardLike) {
            board_like_controller("unLike");
        } else {
            board_like_controller("like");
        }

        // 북마크 버튼 제어
        if (boardBookmark) {
            board_bookmark_controller("unBookmark");
        } else {
            board_bookmark_controller("bookmark");
        }

        // 사진 페이징 표기 제어
        if (fileSize > 1) {
            board_img_paging_controller(i);
        }
    }).catch(err => {
        alert("detail 실패");
    });


}
const board_like = (id) => {
    axios({
        method: "post",
        url: "/board/like/" + id + "?boardKind=" + boardKind
    }).then(res => {
        board_like_controller("unLike");
    }).catch(err => {
        alert("like 실패");
    });
}
const board_unLike = (id) => {
    axios({
        method: "post",
        url: "/board/unLike/" + id + "?boardKind=" + boardKind
    }).then(res => {
        board_like_controller("like");
    }).catch(err => {
        alert("unLike 실패");
    });
}

const board_bookmark = (id) => {
    axios({
        method: "post",
        url: "/board/bookmark/" + id + "?boardKind=" + boardKind
    }).then(res => {
        board_bookmark_controller("unBookmark");
    }).catch(err => {
        alert("bookmark 실패");
    });
}
const board_unBookmark = (id) => {
    axios({
        method: "post",
        url: "/board/unBookmark/" + id + "?boardKind=" + boardKind
    }).then(res => {
        board_bookmark_controller("bookmark");
    }).catch(err => {
        alert("unBookmark 실패");
    });
}

const detail_off = () => {
    i = 0;
    mainDetail.style.display = "none";
}

const detail_file_next = (id) => {
    i += 1;
    openMainDetail(id);
}

const detail_file_back = (id) => {
    i -= 1;
    openMainDetail(id);
}

const nextBtn_controller = (type) => {
    const detailNextBtn = document.getElementById("detail-next-btn");
    if (type == "off") {
        detailNextBtn.style.display = "none";
    } else if (type == "on") {
        detailNextBtn.style.display = "";
    }
}
const backBtn_controller = (type) => {
    const detailBackBtn = document.getElementById("detail-back-btn");
    if (type == "off") {
        detailBackBtn.style.display = "none";
    } else if (type == "on") {
        detailBackBtn.style.display = "";
    }
}

const board_like_controller = (type) => {
    const boardLikeBtn = document.getElementById("board-like");
    const boardUnLikeBtn = document.getElementById("board-unLike");
    if (type == "unLike") {
        boardLikeBtn.style.display = "none";
        boardUnLikeBtn.style.display = "";
    } else {
        boardLikeBtn.style.display = "";
        boardUnLikeBtn.style.display = "none";
    }
}

const board_bookmark_controller = (type) => {
    const boardBookmarkBtn = document.getElementById("board-bookmark");
    const boardUnBookmarkBtn = document.getElementById("board-unBookmark");
    if (type == "unBookmark") {
        boardBookmarkBtn.style.display = "none";
        boardUnBookmarkBtn.style.display = "";
    } else {
        boardBookmarkBtn.style.display = "";
        boardUnBookmarkBtn.style.display = "none";
    }
}

const board_img_paging_controller = (imgPage) => {
    for (let p = 0; p < fileSize; p++) {
        // 부모 요소 선택
        var parentElement = document.querySelector('.detail-img-bottom-num-box');

        // 자식 요소 생성
        var childElement = document.createElement('div');
        childElement.className = 'detail-img-bottom-num-on';
        var childElementOff = document.createElement('div');
        childElementOff.className = 'detail-img-bottom-num-off';

        // 자식 요소를 부모 요소의 하위로 추가
        if (p == imgPage) {
            parentElement.appendChild(childElementOff);
        } else {
            parentElement.appendChild(childElement);
        }
    }
}