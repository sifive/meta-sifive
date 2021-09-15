require conf/machine/include/riscv/tune-riscv.inc

TUNEVALID[sifive-5-series] = "SiFive 5 Series Cores"
TUNEVALID[sifive-7-series] = "SiFive 7 Series Cores"

AVAILTUNES += " sifive-5-series sifive-7-series"

# SiFive 5 Series Cores
TUNE_FEATURES:tune-sifive-5-series = "${TUNE_FEATURES:tune-riscv64} sifive-5-series"
TUNE_ARCH:tune-sifive-5-series = "riscv64"
TUNE_PKGARCH:tune-sifive-5-series = "riscv64"
PACKAGE_EXTRA_ARCHS:tune-sifive-5-series = "riscv64"

# SiFive 7 Series Cores
TUNE_FEATURES:tune-sifive-7-series = "${TUNE_FEATURES:tune-riscv64} sifive-7-series"
TUNE_ARCH:tune-sifive-7-series = "riscv64"
TUNE_PKGARCH:tune-sifive-7-series = "riscv64"
PACKAGE_EXTRA_ARCHS:tune-sifive-7-series = "riscv64"

TUNE_CCARGS:append = "${@bb.utils.contains('TUNE_FEATURES', 'sifive-5-series', ' -mtune=sifive-5-series', ' ', d)}"
TUNE_CCARGS:append = "${@bb.utils.contains('TUNE_FEATURES', 'sifive-7-series', ' -mtune=sifive-7-series', ' ', d)}"
