FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://hostapd.conf"

do_install_append () {
    install -d ${D}/${sysconfdir}
    cp ${WORKDIR}/hostapd.conf ${D}/${sysconfdir}/hostapd.conf

    #echo "interface=wlan0" > ${D}/${sysconfdir}/hostapd.conf
    #echo "ssid=Example-WLAN" >> ${D}/${sysconfdir}/hostapd.conf
    #echo "hw_mode=g" >> ${D}/${sysconfdir}/hostapd.conf
    #echo "wpa=3" >> ${D}/${sysconfdir}/hostapd.conf
    #echo "wpa_passphrase=montavista"  >> ${D}/${sysconfdir}/hostapd.conf
    #echo "wpa_key_mgmt=WPA-PSK WPA-EAP" >> ${D}/${sysconfdir}/hostapd.conf
    ##echo "wpa_pairwise=TKIP" >>  ${D}/${sysconfdir}/hostapd.conf
    #echo "rsn_pairwise=CCMP" >>   ${D}/${sysconfdir}/hostapd.conf
}
