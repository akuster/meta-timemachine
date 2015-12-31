#FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append () {
    install -d ${D}/${sysconfdir}/dhcp
    install -d ${D}/${sysconfdir}/default

cat >tm_dhcpd.conf <<EOF
default-lease-time 600;
max-lease-time 7200;
option domain-name-servers 127.0.0.1;
option domain-search "example.com";

subnet ${TM_HOST_NETWORK} netmask ${TM_HOST_NETMASK} {
    range ${TM_HOST_DHCP_START} ${TM_HOST_DHCP_END};
        option routers ${TM_HOST_GW};
 }
EOF


cat >tm_dhcpd-server <<EOF
INTERFACES="${TM_HOST_DEV}"
EOF

    install ${B}/tm_dhcpd.conf ${D}/${sysconfdir}/dhcp/dhcpd.conf
    install ${B}/tm_dhcpd-server ${D}/${sysconfdir}/default/dhcp-server
}
