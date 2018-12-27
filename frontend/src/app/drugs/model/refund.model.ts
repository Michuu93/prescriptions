export class Refund {
    public level: string;
    public description: string;

    constructor(level: string, description: string) {
        this.level = level;
        this.description = description;
    }
}