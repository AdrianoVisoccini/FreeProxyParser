#!/bin/sh

# Default configuration
DEFAULT_PROXYCHAINS_CONF="/etc/proxychains4.conf"
DEFAULT_PROXY_LIST="$(dirname "$0")/proxylist"

# Edit this array to enable/disable protocols
ALLOWED_PROTOCOLS=("http" "socks4" "socks5")

while getopts ":c:p:" opt; do
  case $opt in
    c) PROXYCHAINS_CONF="$OPTARG" ;;
    p) PROXY_LIST="$OPTARG" ;;
    \?) echo "Invalid option: -$OPTARG" >&2; exit 1 ;;
  esac
done

PROXYCHAINS_CONF="${PROXYCHAINS_CONF:-$DEFAULT_PROXYCHAINS_CONF}"
PROXY_LIST="${PROXY_LIST:-$DEFAULT_PROXY_LIST}"

[ -f "$PROXYCHAINS_CONF" ] || { echo "Error: proxychains config not found: $PROXYCHAINS_CONF"; exit 1; }
[ -f "$PROXY_LIST" ] || { echo "Error: proxy list not found: $PROXY_LIST"; exit 1; }

sed -i '/^\[ProxyList\]/,/^$/ {/^\[ProxyList\]/!{/^$/!d}}' "$PROXYCHAINS_CONF"

added_count=0
while IFS= read -r line || [ -n "$line" ]; do
  line=$(echo "$line" | tr -d '\r\n' | xargs)

  [ -z "$line" ] || [ "${line#\#}" != "$line" ] && continue

  proto=$(echo "$line" | awk '{print $1}')

  case " ${ALLOWED_PROTOCOLS[*]} " in
    *" $proto "*)
      echo "$line" >> "$PROXYCHAINS_CONF"
      added_count=$((added_count + 1))
      ;;
  esac
done < "$PROXY_LIST"

[ $added_count -gt 0 ] && sed -i '/^\[ProxyList\]/a\\' "$PROXYCHAINS_CONF"

echo "Successfully updated $added_count proxies in:"
echo "Config: $PROXYCHAINS_CONF"