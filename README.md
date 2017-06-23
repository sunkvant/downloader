# Downloader
Консольное приложение для загрузки файлов по HTTP протоколу.

### Параметры:
```-l``` -ссылка на скачиваемый файл.

```-f``` -путь к файлу, содержащему ссылки.

```-p``` -указывает путь для сохранения файлов на файловой системе 

```-t``` -количество потоков (максимум 30)

```-n``` -указывает имя файла для сохранения (используется в паре с ```-l```).

### Приложение поддерживает 3 типа файлов:
+ CSV
+ JSON
+ XML

#### Пример входного CSV:

```csv
http://lostfilm.info/images/photo/100/21037471_992911.jpg 1.jpg
http://lostfilm.info/images/photo/99/443293_984659.jpg 2.jpg
http://mathprofi.ru/goryachie_formuly.pdf goryachie_formuly.pdf
http://mathprofi.ru/trigonometricheskie_tablicy.pdf trigonometricheskie_tablicy.pdf
```
#### Пример входного JSON:

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

#### Пример входного XML:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<links>

	<link>	<url>http://lostfilm.info/images/photo/100/21037471_992911.jpg</url>
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
 Приложение находиться в папке target/downloader.jar
```
