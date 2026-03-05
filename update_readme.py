import os
import re
import requests

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

LEETCODE_USERNAME = "_AnuragKumar_"

def get_leetcode_stats():
    url = f"https://leetcode-stats-api.herokuapp.com/{LEETCODE_USERNAME}"
    try:
        res = requests.get(url)
        data = res.json()

        return {
            "total": data["totalSolved"],
            "easy": data["easySolved"],
            "medium": data["mediumSolved"],
            "hard": data["hardSolved"],
            "ranking": data.get("ranking", "N/A"),
        }
    except:
        return None

stats = get_leetcode_stats()

if stats:
    stats_section = f"""
## LeetCode Stats

Total Solved: {stats['total']}

Easy: {stats['easy']}
Medium: {stats['medium']}
Hard: {stats['hard']}

Global Ranking: {stats['ranking']}
"""
else:
    stats_section = "LeetCode stats unavailable"

readme = re.sub(
    r"<!-- LEETCODE-STATS-START -->[\s\S]*<!-- LEETCODE-STATS-END -->",
    f"<!-- LEETCODE-STATS-START -->\n{stats_section}\n<!-- LEETCODE-STATS-END -->",
    readme
)

with open(README_FILE, "w", encoding="utf-8") as f:
    f.write(readme)
