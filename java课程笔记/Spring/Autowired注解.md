## @Autowired

- 构造函数

```java
@Autowired
public CDplay(CompactDisc cd, Power power) {
    this.cd = cd;
    this.power = power;
    System.out.println("CDplay多参...");
}
```

- 成员变量

```java
@Autowired
private CompactDisc cd;
@Autowired
private Power power;
```

- setter方法

```java
@Autowired
public void setCd(CompactDisc cd) {
    this.cd = cd;
}
@Autowired
public void setPower(Power power) {
    this.power = power;
}
```

- 任意方法

```java
@Autowired
public void init(CompactDisc cd, Power power) {
    this.cd = cd;
    this.power = power;
}

```

类

```java
@Component
public class CDplay {
    private CompactDisc cd;
    private Power power;

	public CDplay() {
    	System.out.println("CDplay无参...");
	}

	public void play() {
    	power.supply();
    	cd.play();
	}
}
```
## required属性：@Autowired(required=false)

表示注入的对象是可选的，required默认为true，表示对象必须存在

