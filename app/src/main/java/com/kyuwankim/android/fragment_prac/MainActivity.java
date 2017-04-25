package com.kyuwankim.android.fragment_prac;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    // 1. 사용할 프래그먼트 선언
    ListFragment list;
    DetailFragment detail;
    FragmentManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. 프래그먼트 생성
        list = new ListFragment();
        detail = new DetailFragment();
        // 리스트 프래그먼트에 나자신을 넘겨준다.
        list.setActivity(this);
        detail.setActivity(this);

        // 3. 프래그먼트 매니저 가져오기
        manager = getSupportFragmentManager();

        setList();
    }


    
    // Activity 에 처음 목록이 세팅될때
    public void setList(){
        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
        FragmentTransaction transaction = manager.beginTransaction();
        // 2. 프래그먼트를 레이아웃에 add 한다
        transaction.add(R.id.fragment, list);
        // 최초 호출되는 프래그먼트는 addToBackStack 을 사용하지 않는다
        //transaction.addToBackStack(null);
        // 3. git 의 commit 과 같은 기능
        transaction.commit();
    }

    // 리스트에서 상세로 이동할때
    public void goDetail(){
        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
        FragmentTransaction transaction = manager.beginTransaction();
        // 2. 프래그먼트를 레이아웃에 add 한다
        transaction.add(R.id.fragment, detail);
        // 3. 커밋전에 트랜잭션 전체를 stack 에 저장을 합니다. 뒤로가기시 스택관리를 할 수 있습니다.
        transaction.addToBackStack(null);
        // 4. git 의 commit 과 같은 기능
        transaction.commit();
    }

    // Detail 프래그먼트에서 List 로 돌아갈때
    public void backToList(){
        // 뒤로가기로 스택을 빼내면 된다 - popBackStack 에 해당하는 함수이다.
        super.onBackPressed();
//        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
//        FragmentTransaction transaction = manager.beginTransaction();
//        // 2. detail 프래그먼트를 스택에서 제거한다
//        transaction.remove(detail);
//        // 3. 커밋
//        transaction.commit();
    }


}
