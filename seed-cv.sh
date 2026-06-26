#!/usr/bin/env bash
# Carga todos los datos del CV vía la API.
# Uso:  bash seed-cv.sh
# Edita BASE_URL y los datos de ejemplo con tu información real.
set -e

BASE_URL="https://cv-api-xxxx.onrender.com"   # <-- CAMBIA por tu URL real de Render
API_KEY="app-cv"
API_VALUE="wIRE5U/rq+vInaTX5RYjEZQA9jLTn8p7hCZ7bpSV2ONUvw+vvn+tJSmI8Dg8l3ZpQK7Q1hkeULTfsqufDyQUbpn9Z/YErm2+VPM3/xO+H+22VpeTQr4LG30dpKnTdxnO+1jwWfpaAWuf6irUHVpbbeFUprfCO0apSOQ/3QaN+qJLLx+BzqQ14cBlJhHsNoFYu+bdd9sg2W/3gfiN3QDAZnKOVDpvuFHErju8XAUfptiGqpzOl+yOv3zvbADisSKeTXmzKlktKwouSwfZmpqmvWucRcNSb7lXJHsVrjfCvO14740j9r9gOVnj0BY8VPesZTqzNT+8PZkwvXfDsqSpHA=="

# Helper: $1=metodo  $2=path  $3=json
req() { curl -s -X "$1" "$BASE_URL$2" \
  -H "Content-Type: application/json" \
  -H "X-API-Key: $API_KEY" \
  -H "X-API-Value: $API_VALUE" \
  -d "$3"; echo; }

echo ">> Info personal"
req PUT /api/personal-info '{
  "name": "Cristhian",
  "title": "Desarrollador Full-Stack",
  "email": "ing.cristhianher@gmail.com",
  "phone": "+51 999999999",
  "location": "Lima, Perú",
  "summary": "Desarrollador con experiencia en Java, Spring Boot y tecnologías web.",
  "linkedin": "linkedin.com/in/cristhian",
  "github": "github.com/skatecost1992",
  "website": ""
}'

echo ">> Formación profesional"
req POST /api/educations '{
  "institution": "Universidad Nacional de Ingeniería",
  "degree": "Ingeniería de Sistemas",
  "description": "Especialización en desarrollo de software.",
  "startDate": "2016-03-01",
  "endDate": "2021-12-15",
  "location": "Lima, Perú",
  "orderIndex": 1
}'

echo ">> Experiencia laboral"
req POST /api/experiences '{
  "company": "Tech Corp",
  "role": "Full-Stack Developer",
  "description": "Desarrollo de aplicaciones web con Spring Boot y React.",
  "startDate": "2022-01-01",
  "endDate": "",
  "location": "Lima",
  "orderIndex": 1
}'

echo ">> Idiomas"
req POST /api/languages '{ "name": "Español", "level": "Nativo" }'
req POST /api/languages '{ "name": "Inglés", "level": "Intermedio" }'

echo ">> Certificaciones"
req POST /api/certifications '{
  "name": "AWS Certified Cloud Practitioner",
  "issuer": "Amazon Web Services",
  "date": "2024-06-01",
  "url": "",
  "description": ""
}'

echo ">> Conocimientos de programación"
req POST /api/programming-knowledge '{ "name": "Java", "level": "Avanzado" }'
req POST /api/programming-knowledge '{ "name": "Spring Boot", "level": "Avanzado" }'

echo ">> Conocimientos de testing"
req POST /api/testing-knowledge '{ "name": "Selenium", "level": "Intermedio" }'
req POST /api/testing-knowledge '{ "name": "JUnit", "level": "Avanzado" }'

echo ">> Listo. Revisa tu CV en $BASE_URL"
