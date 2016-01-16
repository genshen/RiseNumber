# RiseNumber
RiseNumber Animator on Android
#ScreenShot
![](ScreenShot/device-2016-01-16-151426.gif)
# How to Use
just copy [app\src\main\java\com\holo\risenumber\views\RiseNumberTextView.java](app/src/main/java/com/holo/risenumber/views/RiseNumberTextView.java)
file to your project!
 ### in layout:
 ``` xml
  <com.yourpackageName.RiseNumberTextView
            android:id="@+id/money"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textColor="@color/red"
            android:textSize="30sp" />
 ```
  ### in Activity
  RiseNumber for int number
  ``` java
   RiseNumberTextView rnt;
   rnt = (RiseNumberTextView) findViewById(R.id.rise);
        rnt.setRiseInterval(1, 99)
                .setDuration(3000)
                .runInt(true)
                .start();

        rnt.setOnRiseEndListener(new RiseNumberTextView.RiseListener() {
            @Override
            public void onRiseEndFinish() {
                ...
            }
        });
  ```
   RiseNumber for float number
  ``` java
   RiseNumberTextView money;
   money = (RiseNumberTextView) findViewById(R.id.money);
        money.setRiseInterval(0, 120)
                .setDuration(3000)
                .runInt(false)
                .setDecimal(2)  //2 digits after the decimal point
                .start();

        money.setOnRiseEndListener(new RiseNumberTextView.RiseListener() {
            @Override
            public void onRiseEndFinish() {
                Snackbar.make(rnt, "Money end", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
  ```
   
