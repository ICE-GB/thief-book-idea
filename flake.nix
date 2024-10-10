{
  description = "A Nix-flake-based Java development environment";

  inputs.nixpkgs.url = "https://flakehub.com/f/NixOS/nixpkgs/0.1.*.tar.gz";

  outputs = {
    self,
    nixpkgs,
  }: let
    supportedSystems = ["x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin"];
    forEachSupportedSystem = f:
      nixpkgs.lib.genAttrs supportedSystems (system:
        f {
          pkgs = import nixpkgs {inherit system;};
        });
  in {
    devShells = forEachSupportedSystem ({pkgs}: let
      libraries = pkgs.lib.makeLibraryPath (with pkgs;
        with xorg; [
          libXtst
          libXext
          libX11
          libXrender
          libXi
          freetype
          fontconfig.lib
          zlib
          libsecret
        ]);
      gradle-wrapped = pkgs.writeShellScriptBin "gradle" ''
        export GRADLE_USER_HOME="$HOME/.local/share/gradle"
        export LD_LIBRARY_PATH=${libraries}
        exec ${pkgs.gradle.override {
          javaToolchains = [
            # "${pkgs.jdk8}/lib/openjdk"
            # "${pkgs.jdk11}/lib/openjdk"
            # "${pkgs.jdk17}/lib/openjdk"
            "${pkgs.jdk21}/lib/openjdk"
          ];
        }}/bin/gradle "$@"
      '';
    in {
      default = pkgs.mkShell {
        packages = [gradle-wrapped pkgs.kubectl];
        shellHook = ''
          ${gradle-wrapped}/bin/gradle --version
        '';
      };
    });
  };
}
