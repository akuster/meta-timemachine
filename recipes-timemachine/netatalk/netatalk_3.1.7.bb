DESCIPTION = "Netatalk is a freely-available Open Source AFP fileserver. A *NIX/*BSD system running Netatalk is capable of serving many Macintosh clients simultaneously as an AppleShare file server (AFP)."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYRIGHT;md5=e076be91a5d0821ea2187e754e689127 \
                    file://libevent/LICENSE;md5=45c5316ff684bcfe2f9f86d8b1279559"

DEPENDS = "db libtool"

SRC_URI = "http://downloads.sourceforge.net/project/${BPN}/${BPN}/${PV}/${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "831ec8bf9e084b64f965d16c528af299"
SRC_URI[sha256sum] = "e4049399e4e7d477f843a9ec4bd64f70eb7c7af946e890311140fd8fbd4bc071"

SRC_URI += "file://no_acl_build_fix.patch"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools pkgconfig systemd

#disable admin group
#http://sourceforge.net/p/netatalk/bugs/574

EXTRA_OECONF = "--disable-admin-group \
    --with-init-style=debian-sysv  \
    --with-bdb=${STAGING_LIBDIR}/.. \
    --with-ssl-dir=${STAGING_LIBDIR}/.. \
    --with-uams-path=${libdir}/netatalk \
    "  

PACKAGECONFIG ?= "libevent gcrypt"
PACKAGECONFIG += "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)}"

PACKAGECONFIG[acls] = "--with-acls, --with-acls=no, acl"
PACKAGECONFIG[conv] = "--with-libiconv=${STAGING_INCDIR}/.., --with-libiconv=no"
PACKAGECONFIG[gssapi] = "--with-gssapi=${STAGING_INCDIR}/.., --with-gssapi=no"
PACKAGECONFIG[kerb] = "--with-kerberos, --with-kerberos=no"
PACKAGECONFIG[ldap] = "--with-ldap=${STAGING_INCDIR}/.., --with-ldap=no"
PACKAGECONFIG[libevent] = "--with-libevent-header=${STAGING_INCDIR} --with-libevent-lib=${STAGING_LIBDIR} --with-libevent, --with-libevent=no, libevent"

PACKAGECONFIG[gcrypt] = "--with-libgcrypt-dir=${STAGING_LIBDIR}/.., --with-libgcrypt-dir=no,libgcrypt, libcrypto"
PACKAGECONFIG[pam] = "--with-pam=${STAGING_LIBDIR}/.., -with-pam=no, libpam"

do_configure_append () {
    cd ${S}/libevent
    ./autogen.sh
}

#include symlink files
FILES_${PN}-dev += "${libdir}/netatalk/uams_dhx.so ${libdir}/netatalk/uams_clrtxt.so"

RDEPENDS_${PN} = "glib-2.0 dbus-glib perl"
