//    게시글 디테일 연결
let i = 0;
let fileSize = 1;



const boardKind = 1;
const openMainDetail = (id, loginId) => {
    console.log("id =" + id);
    const loginid = loginId;

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
        const boardCommentList = board.boardCommentList;
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
            "                                                        \n" +
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
            "\n"
        if (boardDTO.memberId === loginid) {
            output += "<div style=\"padding-right: 8px;\">\n" +
                "                                                                                            <div class=\"detail-board-plus-box\" onclick=\"openBoardMenu(" + boardDTO.id + ")\">\n" +
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
                "                                                                                            </div>\n"+
                "                                                                                       </div>\n"
        }
        ;
        output += "                                                                                    </div>\n" +
            "                                                                                </div>\n" +
            "<div class=\"detail-board-contents-comment\">\n" +
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
            "                                                                                        <ul class=\"detail-board-contents-comment-list-\">\n";
        if(boardCommentList.boardContents !== null){
            output += `
             <div class="detail-board-contents-t-d">
                                                                                                <li class="detail-board-contents-t-" style="border-bottom: 1px solid rgb(239, 239, 239);">
                                                                                                    <div class="detail-board-contents-t-b">
                                                                                                        <div class="detail-board-contents-t-b-">
                                                                                                            <div>
                                                                                                                <div>
                                                                                                                    <div class="detail-board-contents-pr">
                                                                                                                        <a href="#" class="detail-board-contents-pr-link">

                                                                                                                            <!--게시글 작성자의 프로필 사진이 들어가야함-->
                                                                                                                            <img class="detail-board-contents-pr-" src="/upload/${memberDTO.memberProfile}">
                                                                                                                        </a>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>

                                                                                                            <div class="detail-board-contents-t-m">
                                                                                                                <h2 class="detail-board-contents-t-m-n">
                                                                                                                    <div class="detail-board-contents-t-m-n-">
                                                                                                                        <div class="xt0psk2">

                                                                                                                            <!--게시글 작성자 닉네임-->
                                                                                                                            <a href="#" class="detail-board-contents-t-m-n-link" >${memberDTO.memberNickName}</a>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </h2>

                                                                                                                <div class="_a9zs">

                                                                                                                    <!--게시글 내용-->
                                                                                                                    <h1 class="detail-board-contents-t-m-t" >${boardDTO.boardContents}</h1>
                                                                                                                </div>

                                                                                                                <div class="detail-board-contents-mar">
                                                                                                                    <span class="detail-board-contents-mar-">
                                                                                                                        <time class="timeIsGold" datetime="2018-10-01T19:23:59.000Z" title="10월 2, 2018">250주</time>
                                                                                                                    </span>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </li>
                                                                                            </div>
            `
        }

        for (let c in boardCommentList) {
            output += `
<ul class="detail-board-contents-comment-list-1">`
            if (boardCommentList[c].memberId === loginid) {
                output += ` 
    <div class="detail-board-contents-comment-box"  onmouseover="showPlusButton(${boardCommentList[c].id})"
      onmouseout="hidePlusButton(${boardCommentList[c].id})" >
      `
            } else {
                output += `
            <div className="detail-board-contents-comment-box">`
            }
            ;
            output += `
        <li class="detail-board-comment-list-li">
            <div class="detail-board-comment-size">
                <div class="_a9zo">
                    <div>
                        <div>
                            <div class="eotrmftkdydwkdlalwl">
                                <canvas class="_aarh"
                                        height="42"
                                        width="42"></canvas>
                                
                                <!--게시글 상세 댓글을 단 유저의 이미지-->
                                <a href="#"
                                   class="user-myPage-link">
                                    <img class="user-myPage-link-img" src="/upload/${boardCommentList[c].memberProfile}">
                                </a>

                            </div>
                        </div>
                    </div>

                    <div class="_a9zr">
                        <h3 class="_a9zc">
                            <div class="dhfmsWhrakwls">
                                <div style="display: inline;">
                                    <div style="display: inline;">

                                        <!--게시글 상세 댓글을 단 사용자 myPage 이동 링크-->
                                        <a href="#"
                                           class="comment-user-going">${boardCommentList[c].memberNickName}</a>

                                    </div>
                                </div>
                            </div>
                        </h3>

                        <!--게시글 상세 댓글 내용-->
                        <div style="display: inline">
                            <span class="detail-board-comment-text">${boardCommentList[c].commentContents}</span>
                        </div>

                        <div class="detail-board-comment-info">
                            <span class="detail-board-comment-info-line" style="display: flex; align-items: center;">
      
    <!-- Post Detail Comment Time -->
    <a class="detail-board-comment-time-box">
        <time class="detail-board-comment-time"
              datetime="2023-07-02T14:50:05.000Z"
              title="July 2, 2023">3시간 전</time>
    </a>

    <!-- Turn on/off display replies to post detail comments -->
    <button type="button" class="_a9ze">
        <span class="aksemfRKrhalswnd">답글 달기</span>
    </button>
    <button class="Detail-board-plus-btn"
    style="display: none;padding: 0;height: 18px;" id="Detail-board-plus-btn${boardCommentList[c].id}" onclick="openCommentMenu(${boardCommentList[c].id},${boardDTO.id})">
        <div style="align-items: center; display: flex; justify-content: center;">
            <div class="boardMain-main-board-plus-btn-img-box">
                <svg aria-label="More Options"
                     class="_ab6-"
                     color="rgb(115, 115, 115)"
                     fill="rgb(115, 115, 115)"
                     height="24"
                     role="img"
                     viewBox="0 0 24 24"
                     width="24">
                    <circle cx="12" cy="12" r="1.5"></circle>
                    <circle cx="6" cy="12" r="1.5"></circle>
                    <circle cx="18" cy="12" r="1.5"></circle>
                </svg>
            </div>
        </div>
    </button>
</span>
                        </div>
                    </div>
                </div>
            </div>
        </li>
    </div>
</ul>`
        }
        ;
        output += "                                                                                        </ul>\n" +
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
            "                                                                                                            class=\"detail-board-comment-save-textarea\" id=\"board-comment-save-contents\"></textarea>\n" +
            "                                                                                                    <div style=\"margin-left: 8px;cursor: pointer;\" onclick=\"comment_save(" + boardDTO.id + ")\">\n" +
            "                                                                                                        <div class=\"detail-comment-sub-btn-box\">\n" +
            "                                                                                                            <span>게시</span>\n" +
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
            output += `
        <div style="position: fixed;top: 0;width: 100%;z-index: 100;" id="plus-modal-detail" style="display: none";>
               
                </div>`
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
const comment_save = (id) => {
    const commentContents = document.getElementById("board-comment-save-contents").value;

    axios({
        method: "post",
        url: "/board/comment/" + id,
        data: {
            commentContents: commentContents
        }
    }).then(res => {
        const boardComment = res.data;
        openMainDetail(id);
    }).catch(err => {

    });
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


function showPlusButton(CommentId) {
    let ondist = document.getElementById("Detail-board-plus-btn" + CommentId)
    ondist.style.display = 'block';
    console.log("Commentid = " + CommentId);

}

function hidePlusButton(CommentId) {
    let ondist = document.getElementById("Detail-board-plus-btn" + CommentId)
    ondist.style.display = 'none';
    console.log("Commentid = " + CommentId);
}
const fuckyou = () => {
    document.getElementById('plus-modal-detail').style.display = "none";


}
const mainBoardDetailOff = (boardId) => {
    const mainDetail = document.getElementById('mainBoardDetail' + boardId);
    console.log("boardId=" + boardId);
    i = 0;
    mainDetail.style.display = "none";
    mainDetail.innerHTML = "";

}

const commentDelete = (boardid, commentId) => {
    console.log("commentid ="+ commentId);
    console.log("boardId ="+ boardid);
    axios({
        method: "delete",
        url: "/comment/" + commentId
    }).then(res => {
        openMainDetail(boardid)
    }).catch(err => {
        alert("삭제 실패!!");
    });
}
function openCommentMenu(CommentId, boardId) {
    const plusCommentModal = document.getElementById("plus-modal-detail");
    const boardid = boardId;
    const Commentid = CommentId;
    let input = `
     <div class="plus-modal-board-">
         <div class="plus-modal-board-1">
                            <div class="plus-modal-board-2">
                                <div class="plus-modal-board-bg"></div>
                                <div class="plus-modal-board-3">
                                    <div class="plus-modal-board-main-">
                                        <div class="plus-modal-board-main-1">
                                            <div class="plus-modal-board-main-2">
                                                <div class="plus-modal-board-main-3">
                                                    <div class="plus-modal-board-inner-">
                                                        <div class="plus-modal-board-inner-1">
                                                            <div class="plus-modal-board-inner-2">
                                                                <div class="plus-modal-board-inner-3">
                                                                    <button type="button" class="_a9--"
                                                                            onclick="commentDelete(${boardid}, ${Commentid})">
                                                                        삭제
                                                                    </button>
                                                                    <button type="button" class="_a9_1"
                                                                            onclick="fuckyou()">취소
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
        `;
    plusCommentModal.innerHTML = input;
    plusCommentModal.style.display = "";


    console.log("CommentId", Commentid + "boardId = " + boardid);
}
