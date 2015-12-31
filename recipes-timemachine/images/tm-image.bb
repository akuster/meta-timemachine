SUMMARY = "A sample image capable of TimeMachine support."

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_INSTALL_append = " sysklogd hostapd netatalk dhcp-server"
IMAGE_INSTALL_append = " iw wireless-tools usbutils pciutils util-linux"
IMAGE_INSTALL_append = " libnss-mdns avahi-daemon avahi-dnsconfd avahi-autoipd avahi-utils"
IMAGE_INSTALL_append = " wpa-supplicant-passphrase wpa-supplicant-cli wpa-supplicant"

DISTRO_FEATURES_append = " sysvinit"
IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"
