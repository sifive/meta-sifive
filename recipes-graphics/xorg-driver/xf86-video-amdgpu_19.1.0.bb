require recipes-graphics/xorg-driver/xorg-driver-video.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=aabff1606551f9461ccf567739af63dc"

SUMMARY = "X.Org X server -- AMD Radeon video driver"

DESCRIPTION = "Open-source X.org graphics driver for AMD Radeon graphics"

DEPENDS += "virtual/libx11 libxvmc drm \
            virtual/libgl xorgproto libpciaccess"

inherit features_check
REQUIRED_DISTRO_FEATURES += "opengl"

SRC_URI[md5sum] = "55ad19b858e186a2cf4e91ed832c05e7"
SRC_URI[sha256sum] = "4f0ea4e0ae61995ac2b7c72433d31deab63b60c78763020aaa1b28696124fe5d"

RDEPENDS_${PN} += "xserver-xorg-module-exa"
RRECOMMENDS_${PN} += "linux-firmware-radeon"

FILES_${PN} += "${datadir}/X11"
