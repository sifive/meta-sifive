SUMMARY = "nsign is an image creation and signature tool."
DESCRIPTION = "It is used to create bootchain image from individual components (ddr_fw.bin, second_boot_fw.bin, fw_payload.bin)."

LICENSE = "CLOSED"

BRANCH = "dev"
SRCREV = "0ec1fc15df7677ec70506cc3ca81c636a335c308"
SRC_URI = "git://github.com/eswincomputing/Esbd-77serial-nsign.git;branch=${BRANCH};protocol=ssh \
           file://0001-Changes-in-CMakeLists.txt.patch"

SRC_URI[sha256sum] = "f47686b01c23f41895af8ea92942cc19679c075b2e6081b740d9d4647b0c3091"

BBCLASSEXTEND = "native"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit cmake
