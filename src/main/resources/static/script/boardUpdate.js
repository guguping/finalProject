const boardUpdateForm = (id, loginId) =>{
    console.log("id =" + id);
    const loginid = loginId;
    fuckyou2()
    const updateDetail = document.getElementById('mainUpdateDetail');
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

        let output =`
<div id="boardUpdate-layout" style="display: block;">
                                        <div style="z-index: 3;position: relative;">
                                            <div style="box-sizing: border-box;position: relative;z-index: 0;">
                                                <div style="min-height: 100vh;flex-direction: column;display: flex;position: relative;">
                                                    <div class="board-save-form-layout-back"></div>
                                                    <div class="board-save-form-layout-off">
                                                        <div class="board-save-form-layout-off-padding"
                                                             onclick="updateFormOff()">
                                                            <div style="justify-content: center;flex-direction: column;display: flex;align-items: center;">
                                                                <svg aria-label="닫기" class="tpdlqmvhaekerl"
                                                                     color="rgb(255, 255, 255)"
                                                                     fill="rgb(255, 255, 255)" height="18" role="img"
                                                                     viewBox="0 0 24 24"
                                                                     width="18">
                                                                    <title>닫기</title>
                                                                    <polyline fill="none"
                                                                              points="20.643 3.357 12 12 3.353 20.647"
                                                                              stroke="currentColor"
                                                                              stroke-linecap="round"
                                                                              stroke-linejoin="round"
                                                                              stroke-width="3"></polyline>
                                                                    <line fill="none" stroke="currentColor"
                                                                          stroke-linecap="round"
                                                                          stroke-linejoin="round" stroke-width="3"
                                                                          x1="20.649"
                                                                          x2="3.354"
                                                                          y1="20.649" y2="3.354"></line>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                    </div>
     <div class="board-save-form-inherit">
                                                        <div class="board-save-form-1">
                                                            <div class="board-save-form-lr">
                                                                <div class="board-save-form-box">
                                                                    <div class="board-save-form-box-padding">
                                                                        <div></div>
                                                                        <div class="board-save-form-box-inner">
                                                                            <div style="display: flex;flex-direction: column;height: 100%;max-width: 100%;">
                                                                                <div style="max-height: 898px;max-width: 1195px;min-height: 391px;min-width: 688px;width: 1048px;">
                                                                                    <div class="djelRKwlrkskqhwk">
                                                                                        <div class="board-save-form-top-box">
                                                                                            <div style="flex-direction: column;display: flex;align-items: stretch;">
                                                                                                <div class="_ac76">
                                                                                                    <div class="dlrjsEhanjsepmm">
                                                                                                        <h1 class="dlrjsEhanjsepmm-text-box">
                                                                                                            <div class="_ac7a">
                                                                                                                정보 수정
                                                                                                            </div>
                                                                                                        </h1>
                                                                                                    </div>
                                                                                                    <div class="anjswlahfmfdhlsWhr"></div>
                                                                                                    <div class="anjswlahfmfdhfmsWHr">
                                                                                                        <div class="anjswlahfmfdhfmsWHr-margin">
                                                                                                            <div class="anjswlahfmfdhfmsWHr-btn"
                                                                                                                 onclick="boardUpdate(${boardDTO.id}.${loginId})">
                                                                                                                완료
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>

                                                                                        <div class="board-save-form-main-img-box">
                                                                                            <div class="board-save-form-main-img-box-inner">
                                                                                                <div class="board-save-form-main-img-inner">
                                                                                                    <!-- 이미지 프리뷰 페이징 시작-->
                                                                                                    <div class="detail-basis">
                                                                                                        <div class="dlrjsanjfRK">
                                                                                                            <div style="position: relative">
                                                                                                                <div class="wlsWKahfmrpTekdlrjs"></div>
                                                                                                                <div class="detail-img-box">
                                                                                                                    <div class="detail-img-box-inner">
                                                                                                                        <div class="detail-img-box-size">
                                                                                                                            <div class="detail-img-box-div">

                                                                                                                                <!--게시판 상세 이미지 넣을 곳-->
                                                                                                                                <ul class="detail-img-ul">
                                                                                                                                    <li class="detail-img-li">
                                                                                                                                        <div class="detail-img-li-inner">
                                                                                                                                            <div class="detail-img-dlrjsanjfRK">
                                                                                                                                                <div>
                                                                                                                                                    <div class="detail-img-sc">
                                                                                                                                                        <div class="detail-img-sc-inner">
                                                                                                                                                            <img class="detail-re-img-size"
                                                                                                                                                                 src="/upload/${storedFileName}"
                                                                                                                                                            >
                                                                                                                                                        </div>
                                                                                                                                                        <div style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;"></div>
                                                                                                                                                    </div>
                                                                                                                                                </div>
                                                                                                                                            </div>
                                                                                                                                        </div>
                                                                                                                                    </li>
                                                                                                                                </ul>

                                                                                                                            </div>
                                                                                                                        </div>

                                                                                                                        <!--페이지 이동 버튼-->
                                                                                                                        <button aria-label="돌아가기"
                                                                                                                                class="detail-img-back-btn" id="detail-back-btn" style="display: none;" onclick="detail_file_back(${boardDTO.id})">
                                                                                                                            <div class="detail-img-back-btn-logo"></div>
                                                                                                                        </button>
                                                                                                                        <button aria-label="다음"
                                                                                                                                class="detail-img-next-btn" id="detail-next-btn" onclick="detail_file_next(${boardDTO.id})">
                                                                                                                            <div class="detail-img-next-btn-logo"></div>
                                                                                                                        </button>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>

                                                                                                            <!--하단의 페이징 넘버-->
                                                                                                            <div class="detail-img-bottom-num-box">
                                                                                                                <div class="detail-img-bottom-num-on"></div>
                                                                                                                <div class="detail-img-bottom-num-off"></div>
                                                                                                                <div class="detail-img-bottom-num-on"></div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <form enctype="multipart/form-data"
                                                                                                      method="post"
                                                                                                      action="/board/save"
                                                                                                      onsubmit="return image_check()"
                                                                                                      style="margin: 0;padding: 0;">
                                                                                                    <input accept="image/jpeg,image/png,image/heic,image/heif,video/mp4,video/quicktime"
                                                                                                           class="img-input-"
                                                                                                           name="boardFile"
                                                                                                           id="img-input-"
                                                                                                           multiple
                                                                                                           type="file">

                                                                                                    <!--게시글 저장 내용 화면-->
                                                                                                    <div class="board-save-contents-input-box">
                                                                                                        <div class="board-save-contents-input-box-">
                                                                                                            <div class="board-save-contents-input-box-inner">
                                                                                                                <div class="_ac2p">
                                                                                                                    <div class="board-save-contents-input-user-img-box">
                                                                                                                        <div class="_ac2q">
                                                                                                                            <div class="save-input-user-box-inner-size">
                                                                                                                                <div class="save-input-user-img-line">
                                                                                                                                    <div class="wlsWKtjssjasp">
                                                                                                                                        <div class="dlrjseocpdhorlfdjdiehl">
                                                                                                                                            <div class="emrkwkdlalwl">
                                                                                                                                                <div class="emrkwkdlalwlakwls">
                                                                                                                             <span class="emeldjENfgdjTek">

                                                                                                                             <!--게시글 저장공간 사용자 프로필 사진-->
                                                                                                                                <img src="/upload/${memberDTO.memberProfile}"
                                                                                                                                     class="board-save-contents-input-img">
                                                                                                                             </span>
                                                                                                                                                </div>
                                                                                                                                            </div>
                                                                                                                                            <div class="tkdydwkdmldlfma">
                                                                                                                                                <div class="gkrltlfgekwjdakffh">
                                                                                                                                                    <div class="rhwlrksnsdkvdlek">
                                                                                                                                <span class="sorkaksemstmvosdldi">

                                                                                                                                    <!--게시글 저장공간 사용자 닉네임-->
                                                                                                                                   <span text="${memberDTO.memberNickName}"
                                                                                                                                         class="board-save-contents-input-nick">${memberDTO.memberNickName}</span>

                                                                                                                                </span>
                                                                                                                                                    </div>
                                                                                                                                                </div>
                                                                                                                                            </div>
                                                                                                                                        </div>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                    <div class="board-save-contents-text-box">

                                                                                                                        <!--게시글 저장 내용 작성 공간-->
                                                                                                                        <div class="board-save-contents-t">
                                                                                                                            <textarea
                                                                                                                                    class="board-save-input-style"
                                                                                                                                    name="boardContents"
                                                                                                                                    placeholder="${boardDTO.boardContents}">${boardDTO.boardContents}</textarea>
                                                                                                                        </div>

                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>

                                                                                                    <button type="submit"
                                                                                                            style="display: none;"
                                                                                                            id="boardSave-btn">
                                                                                                        저장용
                                                                                                    </button>
                                                                                                </form>
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
                                                    </div>
                                                    </div>
                                                    </div>
                                                    
            `
        updateDetail.style.display = "";
        updateDetail.innerHTML = output;
    }).catch(err => {
        alert("update 실패");
    });
}

const boardUpdate = (id, loginId) => {
    axios({
        method: "put",
        url: "/boardUpdate/" + id
    }).then(res => {
        openMainDetail(boardid)
    }).catch(err => {
        alert("수정 실패!!");
    });
}
const updateFormOff = () => {
    const mainUpdateDetail = document.getElementById('mainUpdateDetail');
    const boardUpdateLayout = document.getElementById("boardUpdate-layout");
    mainUpdateDetail.style.display = "none";
    boardUpdateLayout.style.display = "none";

}
