export default class FileUtils {

    static async getAsByteArray(file: File): Promise<Uint8Array> {
        return new Uint8Array(await this.readFile(file))
    }

    private static readFile(file: File): Promise<any> {
        return new Promise((resolve, reject) => {
          // Create file reader
          let reader = new FileReader()
      
          // Register event listeners
          reader.addEventListener("loadend", e => resolve(e.target!.result))
          reader.addEventListener("error", reject)
      
          // Read file
          reader.readAsArrayBuffer(file)
        })
    }

    static getBase64FromBytes(bytes: Uint8Array): string {
        var binary = '';
        var len = bytes.byteLength;
        for (var i = 0; i < len; i++) {
            binary += String.fromCharCode(bytes[i]);
        }
        return window.btoa(binary);
    }

}