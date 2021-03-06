# Fragment
--
Fragment는 동작 또는 Activity 내에서 사용자 인터페이스의 일부를 나타냅니다. 여러 개의 프래그먼트를 하나의 액티비티에 조합하여 창이 여러 개인 UI를 구축할 수 있으며, 하나의 프래그먼트를 여러 액티비티에서 재사용할 수 있습니다. 프래그먼트는 자체 수명 주기를 가지고, 자체 입력 이벤트를 받으며, 액티비티 실행 중에 추가 및 제거가 가능한 액티비티의 모듈식 섹션이라고 생각하면 됩니다(다른 액티비티에 재사용할 수 있는 "하위 액티비티"와 같은 개념).


프래그먼트를 액티비티 레이아웃의 일부로 추가하는 경우, 이는 액티비티의 뷰 계층 내부의 ViewGroup 안에 살며, 해당 프래그먼트가 자신의 뷰 레이아웃을 정의합니다. 프래그먼트를 액티비티 레이아웃에 삽입하려면 해당 프래그먼트를 액티비티의 레이아웃 파일에서 <fragment> 요소로 선언하거나, 애플리케이션 코드에서 이를 기존의 ViewGroup에 추가하면 됩니다. 그러나 프래그먼트가 액티비티 레이아웃의 일부분이어야만 하는 것은 아닙니다. 나름의 UI가 없는 프래그먼트도 액티비티를 위한 보이지 않는 작업자로 사용할 수 있습니다.

![fragment](https://developer.android.com/images/fundamentals/fragments.png?hl=ko)

##Fragment 생명주기

프래그먼트를 생성하려면 Fragment의 서브클래스(또는 이의 기존 서브클래스)를 생성해야 합니다. Fragment 클래스에는 Activity와 아주 유사해 보이는 코드가 있습니다. 여기에는 액티비티와 비슷한 콜백 메서드가 들어 있습니다. 예를 들어 **onCreate(), onStart(), onPause() 및 onStop()** 등입니다. 사실, 기존 Android 애플리케이션을 변환하여 프래그먼트를 사용하도록 하려면 그저 액티비티의 콜백 메서드에서 프래그먼트에 해당되는 콜백 메서드로 코드를 옮기기만 하면 될 수도 있습니다.

보통은 최소한 다음과 같은 수명 주기 메서드를 구현해야 합니다.

![FragmentLifecycle](https://developer.android.com/images/fragment_lifecycle.png?hl=ko)


>**onCreate()**

>시스템은 프래그먼트를 생성할 때 이것을 호출합니다. 구현 내에서 프래그먼트의 기본 구성 요소 중 프래그먼트가 일시정지되거나 중단되었다가 재개되었을 때 유지하고자 하는 것을 초기화해야 합니다.

>**onCreateView()**
>
>시스템은 프래그먼트가 자신의 사용자 인터페이스를 처음으로 그릴 시간이 되면 이것을 호출합니다. 프래그먼트에 맞는 UI를 그리려면 메서드에서 View를 반환해야 합니다. 이 메서드는 프래그먼트 레이아웃의 루트입니다. 프래그먼트가 UI를 제공하지 않는 경우 null을 반환하면 됩니다.

>**onPause()**

>시스템이 이 메서드를 호출하는 것은 사용자가 프래그먼트를 떠난다는 첫 번째 신호입니다(다만 이것이 항상 프래그먼트가 소멸 중이라는 뜻은 아닙니다). 현재 사용자 세션을 넘어서 지속되어야 하는 변경 사항을 커밋하려면 보통 이곳에서 해야 합니다(사용자가 돌아오지 않을 수 있기 때문입니다).
>
>대부분의 애플리케이션은 각각의 프래그먼트에 이와 같은 메서드를 최소한 세 개씩 구현해야 하지만, 프래그먼트 수명 주기의 여러 단계를 처리하려면 사용해야 하는 다른 콜백 메서드도 많이 있습니다. 모든 수명 주기 콜백 메서드는 나중에 프래그먼트 수명 주기 처리 섹션에서 더욱 상세히 논의할 것입니다.

>이외에도, 기본적인 Fragment 클래스 대신 확장하고자 하는 서브클래스도 몇 개 있을 수 있습니다.

**DialogFragment**

부동 대화상자를 표시합니다. 이 클래스를 사용하여 대화상자를 생성하면 Activity 클래스의 대화상자 도우미 메서드를 사용하는 것의 좋은 대안책이 됩니다. 이렇게 하면 프래그먼트 대화상자를 액티비티가 관리하는 프래그먼트의 백 스택에 통합시킬 수 있어, 사용자가 닫힌 프래그먼트로 돌아갈 수 있도록 해주기 때문입니다.


**ListFragment**

어댑터가 관리하는 항목의 목록(예: SimpleCursorAdapter)을 표시하며, ListActivity와 비슷합니다. 이것은 목록 뷰를 관리하는 데 쓰는 몇 가지 메서드를 제공합니다. 예를 들어 onListItemClick() 콜백을 제공하여 클릭 이벤트를 처리하는 것 등입니다.


**PreferenceFragment**

Preference 객체의 계층을 목록으로 표시하며, PreferenceActivity와 비슷합니다. 이것은 애플리케이션에 대한 "설정" 액티비티를 생성할 때 유용합니다.


##Fragment Control
**Fragment add**
<pre>
<code>
	// 1. 프래그먼트 생성
	Fragment fragment = new Fragment()
	// 2. 프래그먼트를 실행하기위한 트랜잭션 시작
	FragmentTransactiontransaction=manager.beginTransaction();
	// 3. 프래그먼트를 레이아웃에 add 한다
	transaction.add(R.id.fragment, fragment);
	// 4. addToBackStack을 호출하면 stack에 transaction 전체가 쌓인다
	transaction.addToBackStack(null);
	// 5. git 의 commit 과 같은 기능
	transaction.commit();
</code>
</pre>

**addToBackStack** 을 통해 stack을 이용했다면 단순히 onBackPressed 를 호출하는 것만으로 fragment를 제거할 수 있다
<pre>
<code>
// stack에 있는 프래그먼트 제거
super.onBackPressed();
</code>
</pre>