# FireLayout

## Description
FireLayout is a CoordinatorLayout linked to its refence on your Firebase Real-Time Database. You can generate your own layout through firebase console.

## Importing with Gradle
```
compile 'com.fs.firelayout:firelayout:0.1.11'
```

## Importing with Maven
```maven
<dependency>
  <groupId>com.fs.firelayout</groupId>
  <artifactId>firelayout</artifactId>
  <version>0.1.11</version>
  <type>pom</type>
</dependency>
```
## Setup
Configure your project with Firebase in order to use Firebase-Database with the auto-generated json file. Use the firebase plugin built in Android Studio.

## How to
Create an instance of FireLayout in your layout file or by java code.

Example:
```xml
<com.fs.firelayout.FireLayout
        android:id="@+id/fire1"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />     
```       
Note: 'android:id' is mandatory! It's the reference in your firebase database.

Now you have to instantiate the FireLayout:
```java
...
FirebaseDatabase database = FirebaseDatabase.getInstance();
FireLayout fireLayout = (FireLayout) findViewById(R.id.fire1);
fireLayout.init(database);
...
```
Now you're ready to build your own layout! 

Example:

```json
{
  "fire1" : {
    "linearlayout" : {
      "layout_width": "match_parent",
      "layout_height": "wrap_content",
      "orientation": "vertical",
      "children": {
        "textview": {
          "layout_width": "match_parent",
          "layout_height": "wrap_content",
          "text": "Hi FireLayout",
          "textColor": "#FFFFFF",
          "background": "#404040"
        },
        "button": {
          "layout_width": "wrap_content",
          "layout_height": "wrap_content",
          "text": "Click me!",
          "margin_top" : 10,
          "textColor": "#404040",
          "background": "#FFFFFF"
        }
      }
    }
  }
}
```
Import your json in your Firebase database and you're done!

## Views
This library is in development and I'm adding new attributes and views. I'll update the following list each update:

Views : 

- All  

layout_width = integer / "wrap_content" / "match_parent" 

layout_height = integer / "wrap_content" / "match_parent" 

background = "exadecimal_code", see example 

enable = true/false, default true 

visibility = "gone" / "invisible", default visible 

padding_top = integer 

padding_left = integer 

padding_right = integer 

padding_bottom = integer 

margin_top = integer 

margin_left = integer 

margin_right = integer 

margin_bottom = integer

ViewGroups: 

- All:

  children = {"childviewname" : {}}

- linearlayout (LinearLayout) 

  orientation = "vertical" / "horizontal"
  
  gravity = "center" / "center_horizontal" / "center_vertical" / "bottom" / "top" / "left" / "right"

- relativelayout (RelativeLayout) 

  gravity = "center" / "center_horizontal" / "center_vertical" / "bottom" / "top" / "left" / "right"
  
Children Views :

- textview (TextView) 

  text = "text"
  
  textColor = "exadecimal_code", see example
  
  gravity = "center" / "center_horizontal" / "center_vertical" / "bottom" / "top" / "left" / "right"
  
- edittext (EditText) 

  text = "text"
  
  hint = "hint"
  
  textColor = "exadecimal_code", see example
  
  gravity = "center" / "center_horizontal" / "center_vertical" / "bottom" / "top" / "left" / "right"
  
- button (Button) 

  text = "text"
  
  textColor = "exadecimal_code", see example
  
  gravity = "center" / "center_horizontal" / "center_vertical" / "bottom" / "top" / "left" / "right"

## LICENSE

> Copyright 2016 Francesco Stranieri

> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at

>    http://www.apache.org/licenses/LICENSE-2.0

> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
