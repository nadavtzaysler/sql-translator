#!/bin/sh

echo "Starting container..."

# Inject runtime config from env vars
cat <<EOF > /usr/share/nginx/html/config.json
{
  "HEADER_MSG": "${HEADER_MSG}",
  "HEADER_LINK": "${HEADER_LINK}",
  "RUN_LINK": "${RUN_LINK}"
}
EOF

exec "$@"
