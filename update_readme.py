import os
import re

README_FILE = "README.md"

table_header = """| Day | Problem Name | Difficulty | Topic | LeetCode Link |
|----|-------------|------------|-------|---------------|
"""

rows = []

day_folders = sorted(
    [d for d in os.listdir() if re.match(r"Day-\d+", d)],
    key=lambda x: int(x.split("-")[1])
)

for day in day_folders:
    readme_path = os.path.join(day, "README.md")
    if not os.path.exists(readme_path):
        continue

    with open(readme_path, "r", encoding="utf-8") as f:
        content = f.read()

    def extract(label):
        match = re.search(rf"\*\*{label}:\*\*\s*(.+)", content)
        return match.group(1).strip() if match else "-"

    problem = extract("Problem Name")
    difficulty = extract("Difficulty")
    topic = extract("Topic")
    link = extract("LeetCode Link")

    rows.append(f"| {day} | {problem} | {difficulty} | {topic} | {link} |")

with open(README_FILE, "r", encoding="utf-8") as f:
    readme = f.read()

new_table = table_header + "\n".join(rows)

updated_readme = re.sub(
    r"\| Day \| Problem Name \| Difficulty \| Topic \| LeetCode Link \|[\s\S]*",
    new_table,
    readme
)

with open(README_FILE, "w", encoding="utf-8") as f:
    f.write(updated_readme)
