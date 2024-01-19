# TOASTER-AOS
<img src="https://img.shields.io/badge/Kotlin-0095D5?&style=flat-square&logo=kotlin&logoColor=white"/> <img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white"/>

더 이상 링크를 태우지 마세요. 토스트 먹듯이 간단하게!
유저가 링크를 산재된 플랫폼에 저장함으로 인해 발생하는 모든 불편함을 토스터에서 해소해줄 수 있어요.

- 33기 DO SOPT APP-JAM (2023.12.17 ~ )
- Development Environment : `Hedgehog | 2023.1.1`

<br>
Features
Architecture

![KakaoTalk_Photo_2024-01-19-22-08-12](https://github.com/Link-MIND/Toaster_Android/assets/93514333/9b41b62a-60ce-41ed-8a1a-9b3c421d7c0a)

![KakaoTalk_Photo_2024-01-19-22-08-00](https://github.com/Link-MIND/Toaster_Android/assets/93514333/e003aa9e-aa99-4c27-a08c-b78b80f577f5)

Technology Stacks
- Dependency injection with Hilt
- Local Data with DataStore(security)
- Networking with Retrofit,OkHttp
- Service with FireBase
- Utils with detekt,ktlint
- Navigation with JetPack Navigation

Librarys
- KaKao
- orbit mvi

## AOS Developer

| [상욱](operawook@catholic.ac.kr) | [이삭](lsls4868@gmail.com) | [채은](parkchangel@naver.com) | [민영](codingmy@naver.com) |
| :--: | :--: | :--: | :--: |
| <img width="600" alt="상욱" src="https://avatars.githubusercontent.com/u/113014331?v=4"> | <img width="600" alt="이삭" src="https://avatars.githubusercontent.com/u/93514333?v=4"> | <img width="600" alt="채은" src="https://avatars.githubusercontent.com/u/107169027?v=4"> | <img width="600" alt="민영" src="https://avatars.githubusercontent.com/u/97686638?v=4"> |
| <p align = "center">`타이머페이지` | <p align = "center">`메인페이지` `링크저장` | <p align = "center">`로그인, 검색` `마이페이지` | <p align = "center"> `카테고리페이지` |

<br>

## 🎥 Demonstration video

- 링크 저장 플로우 - 링크 1개 저장 과정
https://github.com/Link-MIND/Toaster_Android/assets/97686638/02e3f491-4f2e-443d-9ea4-499d6e4668bf

- 링크 저장 플로우 - 링크 3개 저장과정 (홈뷰에서 카테고리 내 링크 갯수 변화 확인가능)
https://github.com/Link-MIND/Toaster_Android/assets/97686638/04bdc80f-75fc-4ff0-8787-450bc515f1eb


- 마이페이지뷰 기능(탈퇴제외)
https://github.com/Link-MIND/Toaster_Android/assets/97686638/0a97f9a6-92e4-4882-8333-1c5e00d05369


- 세부카테고리뷰 기능 열람,미열람, 삭제기능
https://github.com/Link-MIND/Toaster_Android/assets/97686638/4e77dace-947c-47f4-98b1-e68b2cdd9e23

- 클립 편집 - 클립의 수정한 이름이 검색시에도 수정된 이름으로 검색되는 플로우 확인가능
https://github.com/Link-MIND/Toaster_Android/assets/97686638/d4548492-b3d4-47bf-be76-e83b268df14a



- 타이머 설정 - 요일 개별 선택해서 생성, 타이머 삭제 플로우
https://github.com/Link-MIND/Toaster_Android/assets/97686638/e6c2a363-a694-4d5c-b339-6e2c3aceee5e



- 타이머 추가 플로우 - 알림 권한 설정, 타이머 추가 후 변경한 요일, 시간 적용되는 것까지 확인 가능
https://github.com/Link-MIND/Toaster_Android/assets/97686638/4dfd0100-63b8-4839-87ef-a8d1457587bc



- 홈뷰에서 전체 카테고리 진입 플로우 - (열람,미열람 토글 클릭시 필터링 되는 기능 확인 가능)
https://github.com/Link-MIND/Toaster_Android/assets/97686638/699132ec-86cc-43ad-8b77-714887c74228




- 홈뷰에서 추천링크의 웹뷰 진입, 웹뷰 기능 확인가능
https://github.com/Link-MIND/Toaster_Android/assets/97686638/f772924f-43c0-437c-a2f0-ad5f318b7ab1



## 💻 Code Convention
[NOTION Code Convetion Link](https://hill-agenda-2b0.notion.site/Code-Convention-f492a5bdf5b444a6aae561e53d9d4e10)
</br>

## 🔖 Branch Strategy
[NOTION Branch Strategy](https://hill-agenda-2b0.notion.site/Branch-Strategy-e3a9c5e70f6241ae9ccad544666b095c?pvs=4)
</br>

## 🎁 Git Convention
[NOTION Git Convention](https://hill-agenda-2b0.notion.site/Git-Convention-064dee5df78e4b0c9dd59d18c775a460?pvs=4)
</br>

## 💻 Kanban Board
[GIT Kanban Board](https://github.com/orgs/Link-MIND/projects/1/views/1)
</br>

## 📜 Project Plan
[Project Plan](https://hill-agenda-2b0.notion.site/7a635a2c014c470899899073be2ff49f?v=4de94ec87af045d8ba9a69afa39511af)

### 📂 Module
### 모듈은 추가적으로 변화가능
```bash
├── LinkMind-Android
├── 📁 app
├── 📁 build-logic
├── 📁 Convention
├── 📁 core
│   ├── 🗂️ auth
│   ├── 🗂️ authimpl
│   ├── 🗂️ common
│   ├── 🗂️ datastore
│   ├── 🗂️ model
│   ├── 🗂️ network
│   ├── 🗂️ ui
│   ├── 🗂️ designsystem
├── 📁 data
│   ├── 🗂️ category
│   ├── 🗂️ home
│   ├── 🗂️ link
│   ├── 🗂️ timer
│   ├── 🗂️ user
│   ├── 🗂️ oauthdata
├── 📁 data-local
│   ├── 🗂️ linkminddata-local
├── 📁 data- remote
│   ├── 🗂️ category
│   ├── 🗂️ home
│   ├── 🗂️ link
│   ├── 🗂️ timer
│   ├── 🗂️ user
├── 📁 domain
│   ├── 🗂️ category
│   ├── 🗂️ home
│   ├── 🗂️ link
│   ├── 🗂️ timer
│   ├── 🗂️ user
│   ├── 🗂️ oauthdomain
├── 📁 feature
│   ├── 🗂️ maincontainer
│   ├── 🗂️ home
│   ├── 🗂️ clip
│   ├── 🗂️ login
│   ├── 🗂️ timer
│   ├── 🗂️ mypage
│   ├── 🗂️ savelink
├── 📁 gradle
│   ├──  libs.versions.toml
```


