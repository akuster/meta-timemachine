
do_install_append () {
    echo "auto lo" > ${D}/${sysconfdir}/network/interfaces
    echo "iface lo inet loopback" >> ${D}/${sysconfdir}/network/interfaces
    echo " " >> ${D}/${sysconfdir}/network/interfaces
    echo "auto ${TM_HOST_DEV}" >> ${D}/${sysconfdir}/network/interfaces
    echo "iface ${TM_HOST_DEV} inet static" >> ${D}/${sysconfdir}/network/interfaces
    echo "hostapd /etc/hostapd.conf" >> ${D}/${sysconfdir}/network/interfaces
    echo "address ${TM_HOST_ADDRESS}" >> ${D}/${sysconfdir}/network/interfaces
    echo "netmask ${TM_HOST_NETMASK}" >> ${D}/${sysconfdir}/network/interfaces
}
