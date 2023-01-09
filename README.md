### Android Circular Slider  [![](https://jitpack.io/v/Mindinventory/AndroidCircularSlider.svg)](https://jitpack.io/#Mindinventory/AndroidCircularSlider) ![](https://img.shields.io/github/languages/top/Mindinventory/AndroidCircularSlider)


Create Highly Customizable Circular Slider made with Jetpack compose

### Preview
![image](https://github.com/Mindinventory/AndroidCircularSlider/blob/master/art/circularSliderDemo.gif)

### Features
- Great look and feel with lots of customization
- Customisable circular slider
- Customisable simple thumb
- Simple to Implement

### Attributes
| Attribute | Description |
| ------ | ------ |
| ```maxNum``` | To provide maximum number for the slider |
| ```radiusCircle``` | Radius of the circular slider |
| ```percentageFontSize``` | Font size of the percentage text |
| ```percentageColor``` | Color of the percentage text |
| ```progressWidth``` | Width of the Progress |
| ```animDuration``` | To set duration for the sliding animation |
| ```animDelay``` | To set delay for the sliding animation |
| ```strokeCap``` | To set strokes of the ends |
| ```thumbRadius``` | To set the radius of the thumb |
| ```tickColor``` | To set the color of the minute-like clock arms | 
| ```tickhighlightedColor``` | To set the color of the hour-like clock arms | 
| ```dialColor``` | Dial color |
| ```progressColor``` | Color of the progress bar |
| ```startThumbCircleColor``` | Initial thumb color |
| ```thumbColor``` | Thumb color |
| ```trackColor``` | Track color |
| ```trackOpacity``` | Opacity of the track |
| ```trackWidth``` | Width of the track |
| ```isDisabled``` | Flag to set enabled/disabled circular slider |
| ```staticProgress``` | Static progress in case if isDisabled is true |


### :thinking: How to use it?
1. Add this in your root build.gradle at the end of repositories:
```
allprojects {
          repositories {
              maven { url 'https://jitpack.io' }
          }
}
```
2. Add the dependency in your app's build.gradle file:
```
dependencies {
    implementation 'com.github.Mindinventory:AndroidCircularSlider:X.X.X'
}
```
3. Use this code inside your composable:
```
fun CircularProgressBar(
    maxNum: Int = 50,
    radiusCircle: Dp = 150.dp,
    percentageFontSize: TextUnit = 28.sp,
    percentageColor: Color = DeepBlue,
    progressWidth: Float = 28f,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    strokeCap: StrokeCap = StrokeCap.Round,
    thumbRadius: Float = 40f,
    tickColor: Color = SkyBlue,
    tickhighlightedColor: Color = TextWhite,
    dialColor: Color = DullPurple,
    progressColor: Brush = Brush.linearGradient(colors = listOf(SkyBlue, Color.White)),
    startThumbCircleColor: List<Color> = listOf(SkyBlue, SkyBlue),
    thumbColor: List<Color> = listOf(Color.White, Color.White),
    trackColor: Color = Color.Black,
    trackOpacity: Float = 0.10f,
    trackWidth: Float = 20f,
    isDisabled: Boolean = false,
    staticProgress: Float = 0f,
)
```

# LICENSE!

AndroidCircularSlider is [MIT-licensed](/LICENSE).

# Let us know!
We’d be really happy if you send us links to your projects where you use our component. Just send an email to sales@mindinventory.com And do let us know if you have any questions or suggestion regarding our work.

<a href="https://www.mindinventory.com/contact-us.php?utm_source=gthb&utm_medium=repo&utm_campaign=circularSlider">
<img src="https://github.com/Sammindinventory/MindInventory/blob/main/hirebutton.png" width="203" height="43"  alt="app development">
</a>





