# XML 여행 서비스 관리 SW

이번 학기 XML 수업으로 과제를 하여 XML, DTD, XSD 설계 및 JAVA Swing을 이용한 관리 SW를 만들었습니다. 실제 여행 서비스를 만든 것은 아니고, 이러한 컨셉을 상상하여 구상하였습니다.

### 개발환경

- Java Eclipse Version: 3.18.400.v20200604-0540
- XML : xml version="1.0" encoding="UTF-8"

## XML

저희 서비스에는 여행자(user), 가이드(guide)를 연결해주는 서비스입니다.

그래서 XML에는 4개의 테이블이 있습니다. 

![Untitled](XML%20%E1%84%8B%E1%85%A7%E1%84%92%E1%85%A2%E1%86%BC%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20SW%20e88a487d387a4f0f85ac357b6e6c872b/Untitled.png)

XML은 data_DTDver.xml, data_XSDver.xml 두가지가 있습니다. 이는 각각 DTD, XSD로 구조를 선언한 XML입니다. 

## DTD

XML의 구조에 맞게 설계를 하였습니다.

![Untitled](XML%20%E1%84%8B%E1%85%A7%E1%84%92%E1%85%A2%E1%86%BC%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20SW%20e88a487d387a4f0f85ac357b6e6c872b/Untitled%201.png)

## XSD

총 5개의 파일입니다. 

main.xsd에 4개의 파일을 불러오는 방식입니다. 

user_table.xsd, guide_table.xsd은 include 방식

Travel_product_info.xsd, place_info.xsd는 import 방식을 사용합니다.

![Untitled](XML%20%E1%84%8B%E1%85%A7%E1%84%92%E1%85%A2%E1%86%BC%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20SW%20e88a487d387a4f0f85ac357b6e6c872b/Untitled%202.png)

main.xsd 예시

## 관리자 SW

자바 스윙을 사용하여 XML 파일을 불러와 관리할 수 있는 Sw를 만들었습니다.

### GUI 구조

1) 메인보드

![Untitled](XML%20%E1%84%8B%E1%85%A7%E1%84%92%E1%85%A2%E1%86%BC%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20SW%20e88a487d387a4f0f85ac357b6e6c872b/Untitled%203.png)

- Load: XML 파일을 Load 하는 버튼입니다.
- Make: 새로운 XML을 만드는 버튼입니다.
- Save: XML을 저장하는 버튼입니다.
- Close: 프로그램 종료 버튼입니다.
- Print: XML 파일 전체 출력 버튼입니다.
- 검색 창: Find를 할 때 필요한 검색어 입력 부분입니다.
- Find: Find 실행 버튼입니다.
- Insert/update/delete: XML에 각각 입력, 수정 삭제를 할 수 있는 새 창으로 갑니다.
- 텍스트 창: Print 또는 Find 실행 시, Data 출력을 나타내는 부분입니다.

2) 서브 보드

![Untitled](XML%20%E1%84%8B%E1%85%A7%E1%84%92%E1%85%A2%E1%86%BC%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20SW%20e88a487d387a4f0f85ac357b6e6c872b/Untitled%204.png)

- 상단 콤보 박스: element를 선택하는 부분입니다.
- Submit: 선택 완료시 submit 버튼을 누릅니다.
- Close: 해당 창을 닫습니다.
- 콤보 박스1: 어떤 node type을 넣을지 고릅니다.
- 입력 창 1: insert 할 node 이름을 입력합니다.
- Insert: insert 실행 버튼입니다.
- 콤보 박스2: 어떤 node type을 수정할지 고릅니다.
- 입력 창 2: update 할 새 node 이름을 입력합니다.
- update: update 실행 버튼입니다.
- 콤보 박스3: 어떤 node type을 삭제할지 고릅니다.
- Delete: delete 실행 버튼입니다.

### 내부 파일 구조

![Untitled](XML%20%E1%84%8B%E1%85%A7%E1%84%92%E1%85%A2%E1%86%BC%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20SW%20e88a487d387a4f0f85ac357b6e6c872b/Untitled%205.png)

- data_window 파일은 insert/update/delete 기능을 수행하는 창이 있습니다.
- DOMNodeFind 파일은 find에 사용되는 클래스가 있는 파일입니다.
- DOMTraverse 파일은 print에 사용되는 클래스가 있는 파일입니다.
- gui_project221126 파일은 메인 창입니다. Gui 구현 및 클래스를 불러오는 메인 파일입니다.
- ValidationCheck_DTD 파일은 DTD검증에 사용되는 클래스가 있는 파일입니다.
- ValidationCheck_XSD 파일은 XSD검증에 사용되는 클래스가 있는 파일입니다.

## 추후 개편 사항

추후 개편하여야 할 것으로는 insert/delete/update 와 같은 기능 수행 이후 Validation을 가능하게 하는 기능이 필요합니다. 

element 생성 후 상단 콤보 박스가 업데이트 되도록 하여야합니다.

자세한 메뉴얼은 XML_Finalproject_Report 확인