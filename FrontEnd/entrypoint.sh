#!/bin/sh

echo "Starting container..."

# Inject runtime config from env vars
cat <<EOF > /usr/share/nginx/html/config.json
{
  "MAIN_HEADER_MSG": "${MAIN_HEADER_MSG}",
  "MAIN_HEADER_HYPERLINK": "${MAIN_HEADER_HYPERLINK}",
  "RUN_BUTTON_HYPERLINK": "${RUN_BUTTON_HYPERLINK}",
  "TRINO_DOCS_DNS": "${TRINO_DOCS_DNS}"
}
EOF

exec "$@"
