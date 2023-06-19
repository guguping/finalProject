Outstagram ( SNS )
===
* * *
<br><br/>

![logo](/src/main/resources/static/images/indexImages/Outstagarm.png)
<br><br/>
## 🔗 Link
- **Deploy Site :** https://www.instagram.com/ &ensp; <img src="https://img.shields.io/website?url=http%3A%2F%2Fteam1.space%2Fchting%2F" />

- **Github Repository :** https://github.com/guguping/finalProject-instagram


<br><br/>
## 📖 Summary
> ```Introduce Project```
>  - [💡 Motivation](#-motivation)
>  - [📌 Features](#-features)
>  - [🛠 Stack](#-stack)
>  - [💿 Installation](#-installation)
>  - [📸 Site Screenshot](#-site-screenshot)
>  - [🧑🏻‍💻 Team Member](#-team-member)
#
* * *
#
# 소개
Outstagram은 인천일보아카데미 탈주직전이조의 파이널 프로젝트로  
2023.06.16 ~ 부터 개발을 시작합니다
<br><br/>
## 💡 motivation
팀원들과의 회의를 통해서 우리가 실생활에서 많이 접하는 SNS를
스프링부트로 SNS를 한번 만들어보자 해서
벤치마킹으로 인스타그램을 만들게 되었습니다

- 사람들이 사진이나 동영상을 올리고 사람들끼리 소통을 할 수 있는 홈페이지
- 사진이나 동영상에 댓글을 달거나 좋아요 즐겨찾기까지 해서 필요한 정보들을 두고 볼 수 있습니다
- 친구 또는 유명인을 팔로우 하여 실시간 정보들을 쉽게 접할 수 있고 소통이 가능합니다

위의 내용을 저희 힘으로 직접 만들어보고 싶다고 해서 기획을 하게 되었습니다
<br><br/>
## 📌 Features
- Google, Kakao , facebook 소셜 로그인
- 사람검색
- 릴스(숏츠)
- 실시간 피드
- 알람기능
- 실시간 채팅
- 좋아요,북마크 기능
- 스토리 기능
  <br><br/>
## 🛠 Stack
### ✔️ Front-End
- jQuery v3.6.1
- HTML 5
- CSS 3
- JavaScript 


### ✔️ Back-End
- Java v11.0.18
- Spring Boot v2.7.12


### ✔️ DataBase
- My Sql v8.0.32

### ✔️ Server

- Apache Tomcat v9.0

### ✔️ Open API
- Google Login API
- Kakao Login API
- Kakao Map API
- chat API
  <br><br/>

<br><br/>
## 📸 Site Screenshot
![main](/src/main/resources/static/images/indexImages/OutstagarmMain.png)
## 🧑🏻‍💻 Team Member
- 이호섭 : https://github.com/hi-sooyeon
- 최민호 : https://github.com/joohyun93326
- 이은수 : https://github.com/Zagg2732

<br><br/>
## sql
```sql
create user user_outstagram@localhost identified by '1234';
create database db_outstagarm;
grant all privileges on db_outstagarm.* to user_outstagram@localhost;
```