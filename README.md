# nomorefailure

# tab1 전화번호 구현
## JSON형식의 데이터를 name, number로 각각 받아와 데이터를 저장하고 이를 recycleView로 띄운다.


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/f60856fa-02b5-487a-bfd5-5271f39aa6fa" width="200" height="350"/>


## Floating action button을 절대적인 위치로 구현하여 데이터가 삭제되거나 추가되어도 FAB의 위치는 움직이지 않게 구현했다.


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/4542e5a4-66ba-4483-a531-cd57bb2638ef" width="200" height="350"/>


## Fab 클릭시 새로운 창을 띄우고 전화번호부를 추가하는 기능을 구현했다.


## 추가적으로 원하는 데이터를 받기 위해 이름과 전화번호가 공백이거나 전화번호가 010으로 시작하지 않거나 이외의 다른 문자를 포함하지 않게 해당 데이터가 아니면 예외라는 메세지를 띄우게 한다.


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/f2ac8aa0-35d7-4e0d-b261-b43c4de56fe2" width="200" height="350"/>


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/cc06b3f9-f5ed-4f6f-af58-f7ce1fa9b375" width="200" height="350"/>


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/285f2e72-ed19-41d7-ab0c-88df4e1f4baf" width="200" height="350"/>


## 저장 버튼을 누르면 저장이 진행되고 recycleView에 업데이트를 진행


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/9f9956b0-5ef5-44c1-ae5f-b0ca3192a2e8" width="200" height="350"/>


## recycleView의 데이터를 왼쪽으로 swipe하는 경우 전화 다이얼로 전화번호가 넘어가진다.


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/7d827772-1b87-4315-a78a-e88380f01136" width="200" height="350"/>


## 오른쪽으로 swipe하는 경우 아이템을 삭제하시겠습니까?라는 안내문구와 함께 삭제를 클릭할 시 해당 데이터가 삭제된다.


## 취소를 선택할 경우 삭제는 진행되지 않고 보존


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/f12222e4-f15d-4098-b7dd-752ae2c53e9c" width="200" height="350"/>


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/44a573bc-3edc-4dfb-a616-348ab4b1e779" width="200" height="350"/>


<img src="https://github.com/happy-emart/nomorefailure/assets/138095816/2bbf80c7-50d2-4829-a674-486a3cfdb1d4" width="200" height="350"/>


# tab2 갤러리 구현


## 갤러리 화면을 RecyclerView를 이용해 불러옵니다. 이를 위해 GalleryItem 타입과 GalleryAdapter을 새롭게 정의해 ViewHolder를 생성했으며, Glide 라이브러리를 이용해 이미지를 보다 효율적으로 처리합니다.


<img width="353" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/aae721a7-529d-46e0-822f-6254d861714a">



## 이미지를 클릭할 시, AlertDialog를 이용해 확대된 이미지를 띄웁니다.


<img width="359" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/ed67cf58-352f-42ed-8ed5-4abfb339d60a">


# tab3 To-Do List 구현


## 할 일 목록을 저장할 수 있습니다. 마찬가지로 RecyclerView를 이용했으며, Todo 타입과 TodoAdapter을 새롭게 정의했습니다. 명언 api를 통해 메인에 명언을 띄웁니다.


<img width="358" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/5360127c-6401-4bb5-9ac9-a30c8654f1e6">


<img width="349" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/a48b1b62-8ec3-4616-83ad-c2ba9003dfe7">


## 작성한 목록의 텍스트를 누르면 체크박스와 목록 텍스트가 나타납니다. 텍스트 아래에는 역시나 명언이 나타나며, 체크를 선택하고 확인을 하면 리사이클러뷰에서 취소선이 생긴 텍스트를 확인할 수 있습니다.


<img width="354" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/9013643c-5d6f-44d4-a1ff-4f2863a792cd">


<img width="355" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/6bf692ca-8ee0-413a-ad35-752fcdb8d3c9">


<img width="358" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/839aad93-e1c6-4ab1-861c-1f4a3ca789e6">


## 휴지통 아이콘을 누르면 작성한 할 일을 제거할 수 있습니다. 또한 빈 텍스트를 추가하려 하면 토스트가 뜨며 추가할 수 없음을 알립니다. 추가가 완료될 시 추가가 완료 되었다는 토스트를 띄웁니다.


<img width="357" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/30e5975f-e8e5-4c50-bf3d-120f326a645a">


<img width="469" alt="image" src="https://github.com/happy-emart/nomorefailure/assets/78948663/78043bbf-168f-409c-a113-7fafa1263050">

