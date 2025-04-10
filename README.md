# PSTAG Technical Test 🚗

This is a command-line application that reads car data from XML and CSV files.
You can filter, sort, and display the results in different output formats.

---

## ✅ How to Run

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

- `price_asc`
- `price_desc`
- `year_asc`
- `year_desc`

---

## 📤 Output Format

Use `--output=` to choose how to display results:
```bash
  java -jar car-processor.jar --output=json
```

### Sort options:
- `table`   # default
- `json`
- `xml`


---

## 💱 Currency by Vehicle Type

Use this to show prices by preferred currency per vehicle type:

```bash
  java -jar car-processor.jar --currency=type
```

### Rules:

- SUV → EUR
- Sedan → JPY
- Truck → USD

---

## 🧪 Full Example

```bash
  java -jar car-processor.jar --filter=brand:Toyota,price<30000 --sort=year_desc --output=json --currency=type
```

---

## 🧑‍💻 Requirements

- Java 17
- Maven