# meta-sifive
SiFive BSPs Layer for OpenEmbedded/Yocto

## Description

This is the general hardware-specific BSP overlay for the SiFive boards.

More information can be found at: <https://sifive.com/> (Official Site)

## Dependencies

This layer depends on:

* URI: https://github.com/openembedded/openembedded-core
  * branch: master
  * revision: HEAD
* URI: https://github.com/openembedded/bitbake
  * branch: master
  * revision: HEAD

## Quick Start

**Note: You only need this if you do not have an existing Yocto Project build environment.**

Make sure to [install the `kas` command by Siemens](https://kas.readthedocs.io/en/latest/userguide.html#dependencies-installation) first.

### Create workspace
```bash
mkdir dist && cd dist
git clone https://github.com/sifive/meta-sifive
```

## Available Machines

This layer provides a BSP for the following machines:
* freedom-u540: The SiFive HiFive Unleashed board
* unmatched: The Sifive Unmatched board

But changes are also validated on Qemu RISC-V 64bits, so this layer
also provide a Kas yaml to build this machine.

## Build Images

A console-only image for the supported targets:
```bash
kas build --update meta-sifive/scripts/kas/unmatched.yml
kas build --update meta-sifive/scripts/kas/freedom-u540.yml
kas build --update meta-sifive/scripts/kas/qemuriscv64.yml
```

## Flash the image on SDCard

The image built can be flashed with bmaptools (faster):

```bash
sudo bmaptool copy ../build/tmp-glibc/deploy/images/unmatched/core-image-minimal-unmatched.wic.xz /dev/mmcblk0
```

If bmaptools aren't available, so you can use the dd command:

```bash
xzcat ../build/tmp-glibc/deploy/images/unmatched/core-image-minimal-unmatched.wic.xz | sudo dd of=/dev/mmcblk0 bs=512K iflag=fullblock oflag=direct conv=fsync status=progress
```

## Running in QEMU

Run the 64-bit machine in QEMU using the following command:

```bash
kas shell meta-sifive/scripts/kas/qemuriscv64.yml -c "runqemu nographic slirp"
```

## Execute tests

Tests can be executed automatically using the following command:

```bash
kas shell meta-sifive/scripts/kas/unmatched.yml -c "bitbake core-image-minimal -c testimage"
kas shell meta-sifive/scripts/kas/freedom-u540.yml -c "bitbake core-image-minimal -c testimage"
kas shell meta-sifive/scripts/kas/qemuriscv64.yml -c "bitbake core-image-minimal -c testimage"
```
