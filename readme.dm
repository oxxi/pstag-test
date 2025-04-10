# PSTAG Technical Test 🚗

This is a command-line application that reads car data from XML and CSV files.
You can filter, sort, and display the results in different output formats.

---

## ✅ How to Use

### Run the program:

```bash
java -jar car-processor.jar
```

This runs the app with no filters or sorting.

---

## 🔍 Filters

You can add filters using `--filter=`, separated by commas:

```bash
java -jar car-processor.jar --filter=brand:Toyota,price<30000,date>2023-01-01
```

### Filter options:

- `brand:BrandName`
- `price<amount`
- `price>amount`
- `date<yyyy-MM-dd`
- `date>yyyy-MM-dd`

---

## ↕️ Sorting

Use `--sort=` to sort the results.

```bash
java -jar car-processor.jar --sort=price_asc
```

### Sort options:

- `--sort=price_asc`
- `--sort=price_desc`
- `--sort=year_asc`
- `--sort=year_desc`


---

## 📤 Output format

Use `--output=` to choose the format:

```bash
java -jar car-processor.jar --output=table
```

### Output options:

- `--output=table` (default)
- `--output=json`
- `--output=xml`

---

## 💱 Currency by vehicle type

Use this option to show the price in a different currency based on the vehicle type:

```bash
java -jar car-processor.jar --currency=type
```

### Rules:

- SUV → EUR
- Sedan → JPY
- Truck → USD

---

## 🛠️ Full example

```bash
java -jar car-processor.jar --filter=brand:Toyota,price<30000 --sort=year_desc --output=json --currency=type
```

---


## 🧑‍💻 Requirements

- Java 17
---



Executable file:

```
car-processor.jar
```

---
