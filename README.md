# Downloader
���������� ���������� ��� �������� ������ �� HTTP ���������.

### ���������:
```-l``` -������ �� ����������� ����.

```-f``` -���� � �����, ����������� ������.

```-p``` -��������� ���� ��� ���������� ������ �� �������� ������� 

```-t``` -���������� ������� (�������� 30)

```-n``` -��������� ��� ����� ��� ���������� (������������ � ���� � ```-l```).

### ���������� ������������ 3 ���� ������:
+ CSV
+ JSON
+ XML

#### ������ �������� CSV:

```csv
http://lostfilm.info/images/photo/100/21037471_992911.jpg 1.jpg
http://lostfilm.info/images/photo/99/443293_984659.jpg 2.jpg
http://mathprofi.ru/goryachie_formuly.pdf goryachie_formuly.pdf
http://mathprofi.ru/trigonometricheskie_tablicy.pdf trigonometricheskie_tablicy.pdf
```
#### ������ �������� JSON:

```java
 {
 "links": [
	{
 	"url": "http://lostfilm.info/images/photo/100/21037471_992911.jpg",
 	"filename": "1.jpg"
 	},

 	{
 	"url": "http://lostfilm.info/images/photo/99/443293_984659.jpg",
 	"filename": "2.jpg"
 	},

 	{
 	"url": "http://mathprofi.ru/goryachie_formuly.pdf goryachie_formuly.pdf",
 	"filename": "goryachie_formuly.pdf"
 	}
            ]
}
```

#### ������ �������� XML:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<links>

	<link>								<url>http://lostfilm.info/images/photo/100/21037471_992911.jpg</url>
		<filename>1.jpg</filename>
	</link>

	<link>
		<url>http://lostfilm.info/images/photo/99/443293_984659.jpg</url>
		<filename>2.jpg</filename>
	</link>

	<link>
		<url>http://mathprofi.ru/goryachie_formuly.pdf goryachie_formuly.pdf</url>
		<filename>goryachie_formuly.pdf</filename>
	</link>
	

</links>
```

```
 ���������� ���������� � ����� target/downloader.jar
```